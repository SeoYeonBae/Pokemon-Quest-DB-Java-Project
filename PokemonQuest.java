import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Position;

public class PokemonQuest extends JFrame {
	Connection conn; // DB 연결 Connection 객체참조변수

	final String COVER = "src/res/blueCover.png";	
	final String LOGIN = "src/res/login.png";
	final String CHOICE = "src/res/choice.png";
	final String GAME = "src/res/game.png";
	final String JOIN = "src/res/join.png";
	final String BOOK = "src/res/book.png";
	final String FEEDBACK = "src/res/feedback.png";
	final String INFO = "src/res/my_info.png";
	final String NEWFRIEND = "src/res/newFriend.png";
	final String MANAGER = "src/res/managerChoice.png";
	final String MFEED = "src/res/mFeedback.png";
	final String MPOKE = "src/res/mPokemon.png";
	final int WIN_WIDTH = 805; 		// 전체 frame의 폭
	final int WIN_HEIGHT = 479; 	// 전체 frame의 높이
	boolean check; 		//아이디 중복확인 여부 확인
	boolean user; 		//사용자 확인
	boolean showBook;	 //도감에 그림 그릴 여부 확인

	ImageIcon blueCoverPanel;
	ImageIcon login;
	ImageIcon choice;
	ImageIcon game;
	ImageIcon join;
	ImageIcon book;
	ImageIcon feedback;
	ImageIcon info;
	ImageIcon newFriend;
	ImageIcon newPoke;
	ImageIcon bookPoke;
	ImageIcon manager;
	ImageIcon mFeed;
	ImageIcon mPoke;

	JFrame newFrame = new JFrame();

	CardLayout card;
	JPanel coverPanel;
	JPanel loginPanel;
	JPanel choicePanel;
	JPanel gamePanel;
	JPanel joinPanel;
	JPanel bookPanel;
	JPanel feedbackPanel;
	JPanel infoPanel;
	JPanel newFriendPanel;
	JPanel mChoicePanel;
	JPanel mFeedPanel;
	JPanel mPokePanel;

	JTextField id;				//로그인
	JPasswordField pw;		
	JTextField join_id;			//회원가입
	JPasswordField join_pw;
	JTextField name;
	JTextField phone;
	JTextField email;
	JTextField info_id;			//회원정보
	JPasswordField info_pw;
	JTextField info_name;
	JTextField info_phone;
	JTextField info_email;
	JTextField nickname;		//뽑기
	JTextField poke_name;		//도감
	JTextField book_name;
	JTextField book_nickname;
	JTextField book_type;
	JTextField book_gender;
	JTextField book_height;
	JTextField book_weight;
	JTextField book_character;
	JTextArea fbText;			//유저 피드백
	JTextField fb_phone;		//매니저 피드백
	JTextField fb_email;
	JTextField fb_index;
	JTextArea getFBText;
	JTextArea fbReply;
	JTextField user_index;		//정보 저장을 위한 텍스트 필드
	JTextField poke_index;
	JTextField MPokeIndex;
	JTextField mPokeName;		//매니저 포켓몬
	JTextField mPokeHeight;
	JTextField mPokeWeight;
	JTextField mPokePicture;
	JTextField mPokeBookPicture;
	JTextField mPokeSearch;

	JRadioButton male = new JRadioButton("Male");			
	JRadioButton female = new JRadioButton("Female");		
	JRadioButton info_male = new JRadioButton("Male");			
	JRadioButton info_female = new JRadioButton("Female");		

	JButton startBtn;
	JButton loginBtn;
	JButton joinBtn;
	JButton friendsBtn;
	JButton joinFinishBtn;
	JButton choiceBookBtn;
	JButton gameReturnBtn;
	JButton bookReturnBtn;
	JButton infoBtn;
	JButton FeedbackBtn;
	JButton choiceEndBtn;
	JButton releaseBtn;
	JButton fbReturnBtn;
	JButton fbEnterBtn;
	JButton infoFinishBtn;
	JButton infoReturnBtn;
	JButton idCheckBtn;
	JButton newBtn;
	JButton nickFinishBtn;
	JButton mUserBtn;
	JButton mFeedbackBtn;
	JButton mEndBtn;
	JButton mReturnBtn;
	JButton fbFinishBtn;
	JButton updateNickBtn;
	JButton dropIDBtn;
	JButton mPokeBtn;
	JButton mPokeSaveBtn;
	JButton mPokeDropBtn;
	JButton mPokeNewBtn;
	JButton mPokeReBtn;
	JButton mSearchPokeBtn;
	JButton mPokePrintBtn;
	JButton mPokePreviewBtn;
	
	JList myPokemons = new JList();									// 
	JScrollPane pScroller = new JScrollPane(myPokemons);
	JList Feed = new JList();
	JScrollPane fScroller = new JScrollPane(Feed);
	JList managerPoke = new JList();
	JScrollPane mScroller = new JScrollPane(managerPoke);

	public static void main(String [] args) {
		try {		
			PokemonQuest pokemon = new PokemonQuest();
			pokemon.dbConnectionInit();
		}
		catch(Exception e){}
	}
	public PokemonQuest() {
		blueCoverPanel = new ImageIcon(COVER);
		login = new ImageIcon(LOGIN);
		choice = new ImageIcon(CHOICE);
		game = new ImageIcon(GAME);
		join = new ImageIcon(JOIN);
		book = new ImageIcon(BOOK);
		feedback = new ImageIcon(FEEDBACK);
		info = new ImageIcon(INFO);
		newFriend = new ImageIcon(NEWFRIEND);
		manager = new ImageIcon(MANAGER);
		mFeed = new ImageIcon(MFEED);
		mPoke = new ImageIcon(MPOKE);

		setTitle("포켓몬 퀘스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setLocationRelativeTo(null); 		// 프레임 중앙에 띄우기
		setResizable(false); 				// 프레임 사이즈 고정

		card = new CardLayout();			
		getContentPane().setLayout(card); 	// 레이아웃을 카드로 설정하고 프레임에 씌우기

		coverPanel = new CoverPanel();
		loginPanel = new LoginPanel();
		choicePanel = new ChoicePanel();
		gamePanel = new GamePanel();
		joinPanel = new JoinPanel();
		bookPanel = new BookPanel();
		feedbackPanel = new FeedbackPanel();
		infoPanel = new InfoPanel();
		newFriendPanel = new NewFriendPanel();		
		mChoicePanel = new ManagerChoicePanel();
		mFeedPanel = new ManagerFeedPanel();
		mPokePanel = new ManagerPokePanel();

		pScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myPokemons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myPokemons.setVisibleRowCount(20);
		myPokemons.setFixedCellWidth(190);
		fScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Feed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Feed.setVisibleRowCount(11);
		Feed.setFixedCellWidth(145);
		mScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		managerPoke.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		managerPoke.setVisibleRowCount(13);
		managerPoke.setFixedCellWidth(155);
		
		id = new JTextField(20);
		pw = new JPasswordField(20);
		join_id = new JTextField(20);
		join_pw = new JPasswordField(20);
		name = new JTextField(20);
		phone = new JTextField(20);
		email = new JTextField(50);
		fbText = new JTextArea();
		info_id = new JTextField(20);
		info_pw = new JPasswordField(20);
		info_name = new JTextField(20);
		info_phone = new JTextField(20);
		info_email = new JTextField(50);
		user_index = new JTextField(10);
		poke_index = new JTextField(10);
		nickname = new JTextField(10);
		poke_name = new JTextField(10);
		book_name = new JTextField(10);
		book_nickname = new JTextField(10);
		book_type = new JTextField(10);
		book_gender = new JTextField(10);
		book_height = new JTextField(10);
		book_weight = new JTextField(10);
		book_character = new JTextField(10);
		fb_phone = new JTextField(10);
		fb_email = new JTextField(10);
		fb_index = new JTextField(10);
		getFBText = new JTextArea();
		fbReply = new JTextArea();
		mPokeName = new JTextField(10);	
		mPokeHeight = new JTextField(10);
		mPokeWeight = new JTextField(10);
		mPokePicture = new JTextField(10);
		mPokeBookPicture = new JTextField(10);
		mPokeSearch = new JTextField(10);
		MPokeIndex = new JTextField(10);

		id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		join_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		join_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		phone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fbText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		info_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		info_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		info_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		info_phone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		info_email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		poke_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_nickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_type.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_gender.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_height.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_weight.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		book_character.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fb_phone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fb_email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		getFBText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fbReply.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mPokeName.setBorder(javax.swing.BorderFactory.createEmptyBorder());	
		mPokeHeight.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mPokeWeight.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mPokePicture.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mPokeBookPicture.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		poke_name.setOpaque(false);

		startBtn = new JButton();
		startBtn.setBorderPainted(false);						
		startBtn.setContentAreaFilled(false); 					
		loginBtn = new JButton();
		loginBtn.setBorderPainted(false);						
		loginBtn.setContentAreaFilled(false); 					
		joinBtn = new JButton();
		joinBtn.setBorderPainted(false);						
		joinBtn.setContentAreaFilled(false); 					
		friendsBtn = new JButton();
		friendsBtn.setBorderPainted(false);						
		friendsBtn.setContentAreaFilled(false); 					
		joinFinishBtn = new JButton();
		joinFinishBtn.setBorderPainted(false);						
		joinFinishBtn.setContentAreaFilled(false); 					
		choiceBookBtn = new JButton();
		choiceBookBtn.setBorderPainted(false);						
		choiceBookBtn.setContentAreaFilled(false); 					
		gameReturnBtn = new JButton();
		gameReturnBtn.setBorderPainted(false);						
		gameReturnBtn.setContentAreaFilled(false); 					
		bookReturnBtn = new JButton();
		bookReturnBtn.setBorderPainted(false);						
		bookReturnBtn.setContentAreaFilled(false); 					
		FeedbackBtn = new JButton();
		FeedbackBtn.setBorderPainted(false);						
		FeedbackBtn.setContentAreaFilled(false); 					
		choiceEndBtn = new JButton();
		choiceEndBtn.setBorderPainted(false);						
		choiceEndBtn.setContentAreaFilled(false); 					
		releaseBtn = new JButton();
		releaseBtn.setBorderPainted(false);						
		releaseBtn.setContentAreaFilled(false); 					
		fbEnterBtn = new JButton();
		fbEnterBtn.setBorderPainted(false);						
		fbEnterBtn.setContentAreaFilled(false); 					
		fbReturnBtn = new JButton();
		fbReturnBtn.setBorderPainted(false);						
		fbReturnBtn.setContentAreaFilled(false); 					
		infoBtn = new JButton();
		infoBtn.setBorderPainted(false);
		infoBtn.setContentAreaFilled(false);
		infoFinishBtn = new JButton();
		infoFinishBtn.setBorderPainted(false);
		infoFinishBtn.setContentAreaFilled(false);
		infoReturnBtn = new JButton();
		infoReturnBtn.setBorderPainted(false);
		infoReturnBtn.setContentAreaFilled(false);
		idCheckBtn = new JButton();
		idCheckBtn.setBorderPainted(false);
		idCheckBtn.setContentAreaFilled(false);
		newBtn = new JButton();
		newBtn.setBorderPainted(false);
		newBtn.setContentAreaFilled(false);
		nickFinishBtn = new JButton();
		nickFinishBtn.setBorderPainted(false);
		nickFinishBtn.setContentAreaFilled(false);
		mUserBtn = new JButton();
		mUserBtn.setBorderPainted(false);
		mUserBtn.setContentAreaFilled(false);
		mFeedbackBtn = new JButton();
		mFeedbackBtn.setBorderPainted(false);
		mFeedbackBtn.setContentAreaFilled(false);
		mEndBtn = new JButton();
		mEndBtn.setBorderPainted(false);
		mEndBtn.setContentAreaFilled(false);
		mReturnBtn = new JButton();
		mReturnBtn.setBorderPainted(false);
		mReturnBtn.setContentAreaFilled(false);
		fbFinishBtn = new JButton();
		fbFinishBtn.setBorderPainted(false);
		fbFinishBtn.setContentAreaFilled(false);
		updateNickBtn = new JButton();
		updateNickBtn.setBorderPainted(false);
		updateNickBtn.setContentAreaFilled(false);
		dropIDBtn = new JButton();
		dropIDBtn.setBorderPainted(false);
		dropIDBtn.setContentAreaFilled(false);
		mPokeBtn = new JButton();
		mPokeBtn.setBorderPainted(false);
		mPokeBtn.setContentAreaFilled(false);
		mPokeSaveBtn = new JButton();
		mPokeSaveBtn.setBorderPainted(false);
		mPokeSaveBtn.setContentAreaFilled(false);
		mPokeDropBtn = new JButton();
		mPokeDropBtn.setBorderPainted(false);
		mPokeDropBtn.setContentAreaFilled(false);
		mPokeNewBtn = new JButton();
		mPokeNewBtn.setBorderPainted(false);
		mPokeNewBtn.setContentAreaFilled(false);
		mPokeReBtn = new JButton();
		mPokeReBtn.setBorderPainted(false);
		mPokeReBtn.setContentAreaFilled(false);
		mSearchPokeBtn = new JButton();
		mSearchPokeBtn.setBorderPainted(false);
		mSearchPokeBtn.setContentAreaFilled(false);
		mPokePrintBtn = new JButton();
		mPokePrintBtn.setBorderPainted(false);
		mPokePrintBtn.setContentAreaFilled(false);
		mPokePreviewBtn = new JButton();
		mPokePreviewBtn.setBorderPainted(false);
		mPokePreviewBtn.setContentAreaFilled(false);

		ButtonGroup sex = new ButtonGroup();				// 라디오 버튼 그룹
		sex.add(male);
		sex.add(female);
		male.setSelected(true);
		female.setSelected(false);
		male.setContentAreaFilled(false);
		female.setContentAreaFilled(false);
		ButtonGroup info_sex = new ButtonGroup();				// 라디오 버튼 그룹
		info_sex.add(info_male);
		info_sex.add(info_female);
		info_male.setSelected(true);
		info_female.setSelected(false);
		info_male.setContentAreaFilled(false);
		info_female.setContentAreaFilled(false);

		id.setHorizontalAlignment(JTextField.CENTER);				//중앙정렬
		pw.setHorizontalAlignment(JTextField.CENTER);
		join_id.setHorizontalAlignment(JTextField.CENTER);
		join_pw.setHorizontalAlignment(JTextField.CENTER);
		name.setHorizontalAlignment(JTextField.CENTER);
		phone.setHorizontalAlignment(JTextField.CENTER);
		email.setHorizontalAlignment(JTextField.CENTER);
		info_id.setHorizontalAlignment(JTextField.CENTER);			
		info_pw.setHorizontalAlignment(JTextField.CENTER);
		info_name.setHorizontalAlignment(JTextField.CENTER);
		info_phone.setHorizontalAlignment(JTextField.CENTER);
		info_email.setHorizontalAlignment(JTextField.CENTER);
		nickname.setHorizontalAlignment(JTextField.CENTER);
		poke_name.setHorizontalAlignment(JTextField.CENTER);
		book_name.setHorizontalAlignment(JTextField.CENTER);
		book_nickname.setHorizontalAlignment(JTextField.CENTER);
		book_type.setHorizontalAlignment(JTextField.CENTER);
		book_gender.setHorizontalAlignment(JTextField.CENTER);
		book_height.setHorizontalAlignment(JTextField.CENTER);
		book_weight.setHorizontalAlignment(JTextField.CENTER);
		book_character.setHorizontalAlignment(JTextField.CENTER);
		mPokeName.setHorizontalAlignment(JTextField.CENTER);		
		mPokeHeight.setHorizontalAlignment(JTextField.CENTER);
		mPokeWeight.setHorizontalAlignment(JTextField.CENTER);
		mPokePicture.setHorizontalAlignment(JTextField.CENTER);
		mPokeBookPicture.setHorizontalAlignment(JTextField.CENTER);
		mPokeSearch.setHorizontalAlignment(JTextField.CENTER);

		id.setFont(new Font("한컴 백제 B",Font.PLAIN,25));				//폰트지정
		join_id.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		name.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		male.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		female.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		phone.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		email.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		fbText.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_id.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_name.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_male.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_female.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_phone.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		info_email.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		nickname.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		poke_name.setFont(new Font("한컴 백제 B",Font.PLAIN,25));
		book_name.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_nickname.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_type.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_gender.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_height.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_weight.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		book_character.setFont(new Font("한컴 백제 B",Font.PLAIN,20));
		fb_phone.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		fb_email.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		getFBText.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		fbReply.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		mPokeName.setFont(new Font("한컴 백제 B",Font.PLAIN,15));	
		mPokeHeight.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		mPokeWeight.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		mPokePicture.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		mPokeBookPicture.setFont(new Font("한컴 백제 B",Font.PLAIN,15));
		mPokeSearch.setFont(new Font("한컴 백제 B",Font.PLAIN,25));

		getContentPane().add("coverPanel",coverPanel); 		
		card.show(getContentPane(), "coverPanel"); 							

		myPokemons.addListSelectionListener(new PokemonListListener()); 		//버튼에 액션 리스너 달기
		Feed.addListSelectionListener(new FeedListListener());
		managerPoke.addListSelectionListener(new ManagerPokeListListener());
		startBtn.addActionListener(new BtnListener());		
		joinBtn.addActionListener(new BtnListener());
		friendsBtn.addActionListener(new BtnListener());
		choiceBookBtn.addActionListener(new BtnListener());
		gameReturnBtn.addActionListener(new BtnListener());
		bookReturnBtn.addActionListener(new BtnListener());
		FeedbackBtn.addActionListener(new BtnListener());
		choiceEndBtn.addActionListener(new BtnListener());
		fbReturnBtn.addActionListener(new BtnListener());
		infoBtn.addActionListener(new BtnListener());
		infoReturnBtn.addActionListener(new BtnListener());	
		mFeedbackBtn.addActionListener(new BtnListener());
		mEndBtn.addActionListener(new BtnListener());
		mReturnBtn.addActionListener(new BtnListener());
		mFeedbackBtn.addActionListener(new BtnListener());
		mEndBtn.addActionListener(new BtnListener());
		mReturnBtn.addActionListener(new BtnListener());
		mPokeReBtn.addActionListener(new BtnListener());
		mSearchPokeBtn.addActionListener(new BtnListener());
		mPokeNewBtn.addActionListener(new BtnListener());
		mPokeReBtn.addActionListener(new BtnListener());
		mPokeNewBtn.addActionListener(new BtnListener());
		loginBtn.addActionListener(new LoginBtnListener());
		joinFinishBtn.addActionListener(new JoinFinishBtnListener());
		releaseBtn.addActionListener(new ReleaseButtonListener());
		fbEnterBtn.addActionListener(new fbSaveButtonListener());
		infoFinishBtn.addActionListener(new InfoFinishBtnListener());
		idCheckBtn.addActionListener(new IdCheckListener());
		newBtn.addActionListener(new NewPokeBtnListener());
		nickFinishBtn.addActionListener(new NickFinishBtnListener());
		mUserBtn.addActionListener(new UserDisplayButtonListener());
		mPokePreviewBtn.addActionListener(new PokemonDisplayButtonListener());
		mPokePrintBtn.addActionListener(new PokemonDisplayButtonListener());
		fbFinishBtn.addActionListener(new fbFinishListener());
		updateNickBtn.addActionListener(new UpNickBtnListener());
		dropIDBtn.addActionListener(new DropIDBtnListener());
		mPokeBtn.addActionListener(new ManagerPokemonListener());
		mPokeSaveBtn.addActionListener(new MPokeSaveBtnListener());
		mPokeDropBtn.addActionListener(new DropPokeListener());

		coverPanel.add(startBtn);			//패널에 버튼 추가
		loginPanel.add(loginBtn);
		loginPanel.add(joinBtn);
		joinPanel.add(joinFinishBtn);
		joinPanel.add(idCheckBtn);
		choicePanel.add(friendsBtn);
		choicePanel.add(choiceBookBtn);
		choicePanel.add(choiceEndBtn);
		choicePanel.add(infoBtn);
		gamePanel.add(newBtn);
		gamePanel.add(gameReturnBtn);
		bookPanel.add(bookReturnBtn);
		bookPanel.add(releaseBtn);
		feedbackPanel.add(fbEnterBtn);
		feedbackPanel.add(fbReturnBtn);
		infoPanel.add(FeedbackBtn);
		infoPanel.add(infoFinishBtn);
		infoPanel.add(infoReturnBtn);
		newFriendPanel.add(nickFinishBtn);
		mChoicePanel.add(mUserBtn);
		mChoicePanel.add(mFeedbackBtn);
		mChoicePanel.add(mPokeBtn);
		mChoicePanel.add(mEndBtn);
		bookPanel.add(updateNickBtn);
		infoPanel.add(dropIDBtn);
		mPokePanel.add(mPokeSaveBtn);
		mPokePanel.add(mPokeDropBtn);
		mPokePanel.add(mPokeNewBtn);
		mPokePanel.add(mPokeReBtn);
		mPokePanel.add(mSearchPokeBtn);
		mFeedPanel.add(mReturnBtn);
		mFeedPanel.add(fbFinishBtn);
		bookPanel.add(pScroller);
		mFeedPanel.add(fScroller);
		mPokePanel.add(mScroller);
		mPokePanel.add(mPokePrintBtn);
		mPokePanel.add(mPokePreviewBtn);
		joinPanel.add(male);
		joinPanel.add(female);
		infoPanel.add(info_male);
		infoPanel.add(info_female);
		loginPanel.add(id);		//패널에 텍스트 필드 추가
		loginPanel.add(pw);
		joinPanel.add(join_id);
		joinPanel.add(join_pw);
		joinPanel.add(name);
		joinPanel.add(phone);
		joinPanel.add(email);
		feedbackPanel.add(fbText);
		infoPanel.add(info_id);	
		infoPanel.add(info_pw);
		infoPanel.add(info_name);
		infoPanel.add(info_phone);
		infoPanel.add(info_email);
		newFriendPanel.add(nickname);
		newFriendPanel.add(poke_name);
		bookPanel.add(book_name);
		bookPanel.add(book_nickname);
		bookPanel.add(book_type);
		bookPanel.add(book_gender);
		bookPanel.add(book_height);
		bookPanel.add(book_weight);
		bookPanel.add(book_character);
		mFeedPanel.add(fb_phone);
		mFeedPanel.add(fb_email);
		mFeedPanel.add(getFBText);
		mFeedPanel.add(fbReply);
		mPokePanel.add(mPokeName);
		mPokePanel.add(mPokeHeight);
		mPokePanel.add(mPokeWeight);
		mPokePanel.add(mPokePicture);
		mPokePanel.add(mPokeBookPicture);
		mPokePanel.add(mPokeSearch);
		mPokePanel.add(mPokeReBtn);
		
		requestFocus();
		setFocusable(true);
		setVisible(true); 
	}
	class CoverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(blueCoverPanel.getImage(), 0, 0, null);							// 이미지 그리기
			startBtn.setBounds(302, 336, 190, 44);
		}
	}// CoverPanel class end
	class LoginPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(login.getImage(), 0, 0, null);
			id.setBounds(325, 273, 240, 30);
			pw.setBounds(325, 317, 240, 30);
			loginBtn.setBounds(200, 370, 190, 50);
			joinBtn.setBounds(450, 370, 185, 50);
		}
	}
	class JoinPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(join.getImage(), 0, 0, null);
			joinFinishBtn.setBounds(625, 375, 100, 45);
			idCheckBtn.setBounds(625, 80, 103, 45);
			join_id.setBounds(300,83,300,40);
			join_pw.setBounds(300,141,300,40);
			male.setBounds(340, 230, 100, 100);
			female.setBounds(450,230, 150, 100);
			name.setBounds(300,201,300,40);
			phone.setBounds(300,320,300,40);
			email.setBounds(300,380,300,40);
		}
	}
	class ChoicePanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(choice.getImage(), 0, 0, null);
			friendsBtn.setBounds(235, 40, 335, 75);
			choiceBookBtn.setBounds(235, 140, 335, 75);
			infoBtn.setBounds(235,240,335,75);
			choiceEndBtn.setBounds(235, 340, 335, 75);
		}	
	}
	class GamePanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(game.getImage(), 0, 0, null);	
			newBtn.setBounds(540, 257, 187, 47);
			gameReturnBtn.setBounds(540, 325, 190, 47);
		}
	}
	class NewFriendPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(newFriend.getImage(), 0, 0, null);	
			nickname.setBounds(220, 365, 280,40);
			poke_name.setBounds(230, 35, 100, 40);
			nickFinishBtn.setBounds(510, 365, 85, 40);
			g.drawImage(newPoke.getImage(), 305, 100, null);

		}
	}
	class BookPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(book.getImage(), 0, 0, null);	
			bookReturnBtn.setBounds(100, 385, 185, 42);
			releaseBtn.setBounds(520, 385, 185, 42);
			updateNickBtn.setBounds(310, 385, 180, 42);
			book_name.setBounds(512, 71, 190, 35);
			book_nickname.setBounds(512, 128, 190, 35);
			book_type.setBounds(401, 180, 103, 35);
			book_gender.setBounds(600, 180, 103, 35);
			book_height.setBounds(401, 237, 103, 35);
			book_weight.setBounds(600, 237, 103, 35);
			book_character.setBounds(401, 294, 300, 35);
			pScroller.setBounds(82,42,210,311);
			if(showBook) 
				g.drawImage(bookPoke.getImage(),317,78,null);
		}
	}
	class InfoPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(info.getImage(), 0, 0, null);	
			info_id.setBounds(280,85,300,35);
			info_pw.setBounds(280,130,300,35);
			info_male.setBounds(320, 190, 100, 100);
			info_female.setBounds(410,190, 150, 100);
			info_name.setBounds(280,177,300,35);
			info_phone.setBounds(280,270,300,35);
			info_email.setBounds(280,314,300,35);
			infoFinishBtn.setBounds(112, 370, 133, 42);
			FeedbackBtn.setBounds(268, 370, 133, 42);
			infoReturnBtn.setBounds(574, 370, 133, 42);
			dropIDBtn.setBounds(422, 370, 133, 42);
		}
	}
	class FeedbackPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(feedback.getImage(), 0, 0, null);	
			fbText.setBounds(160, 120, 500, 200);
			fbEnterBtn.setBounds(145, 340, 185, 42);
			fbReturnBtn.setBounds(485, 340, 185, 42);
		}
	}
	class ManagerChoicePanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(manager.getImage(),0,0,null);
			mPokeBtn.setBounds(235, 40, 335, 75);
			mUserBtn.setBounds(235, 140, 335, 75);
			mFeedbackBtn.setBounds(235,240,335,75);
			mEndBtn.setBounds(235, 340, 335, 75);	
		}
	}
	class ManagerPokePanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(mPoke.getImage(),0,0,null);
			mPokeName.setBounds(360, 95, 190, 30);	
			mPokeHeight.setBounds(360, 141, 190, 30);	
			mPokeWeight.setBounds(360, 185, 190, 30);	
			mPokePicture.setBounds(360, 230, 190, 30);
			mPokeSearch.setBounds(88, 345, 226, 38);
			mPokeBookPicture.setBounds(360, 276, 190, 30);
			mPokeSaveBtn.setBounds(582, 67, 135, 38);	
			mPokeDropBtn.setBounds(583, 122, 135, 38);	
			mPokeNewBtn.setBounds(584, 180, 135, 38);	
			mScroller.setBounds(78,71,175,260);
			mPokeReBtn.setBounds(530, 345, 185, 42);
			mSearchPokeBtn.setBounds(330, 345, 185, 42);
			mPokePreviewBtn.setBounds(583, 290, 135, 38);
			mPokePrintBtn.setBounds(583, 236, 135, 38);
		}
	}
	class ManagerFeedPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(mFeed.getImage(),0,0,null);
			fb_phone.setBounds(285,145,210,30);
			fb_email.setBounds(503,145,210,30);
			getFBText.setBounds(285,210,210,125);
			fbReply.setBounds(503,210,210,125);
			fScroller.setBounds(115,145,163,190);
			mReturnBtn.setBounds(485, 340, 185, 42);
			fbFinishBtn.setBounds(145, 340, 185, 42);
		}
	}	
	private void dbConnectionInit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");					// JDBC드라이버를 JVM영역으로 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/배서연proj2", "root", "dmlrhd");	// DB 연결하기
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC 드라이버 클래스를 찾을 수 없습니다 : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB 연결 에러 : " + ex.getMessage());
		}
	}
	public void prepareList() {
		try {
			Statement stmt = conn.createStatement();			// SQL 문을 작성을 위한  Statement 객체 생성
			if(user) {
				ResultSet rs = stmt.executeQuery("SELECT poke_name FROM pokemon NATURAL JOIN user_pokemon up WHERE up.user_id = " 
						+ user_index.getText().trim());			
				Vector<String> poke_list = new Vector<String>();
				while (rs.next()) {
					poke_list.add(rs.getString("poke_name"));
				}
				myPokemons.setListData(poke_list);						
				myPokemons.setSelectedIndex(0);			
			}
			else {
				ResultSet rsF = stmt.executeQuery("SELECT re_id, phone, email, recommend FROM recommend NATURAL JOIN user");
				Vector<String> fb_list = new Vector<String>();		
				while (rsF.next()) {
					fb_list.add(rsF.getString("re_id"));
				}		
				Feed.setListData(fb_list);						
				Feed.setSelectedIndex(0);
				ResultSet rsP = stmt.executeQuery("SELECT * FROM pokemon");
				Vector<String> MPoke = new Vector<String>();		
				while (rsP.next()) {
					MPoke.add(rsP.getString("poke_name"));
				}		
				managerPoke.setListData(MPoke);		
				managerPoke.setSelectedIndex(0);
			}
			stmt.close();// statement는 사용후 닫는 습관
		} catch (SQLException sqlex) {
			System.out.println("SQL 에러 : " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
	}
	public class PokemonListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {					// 리스트의 선택이 바뀔때마다 호출
			if (!lse.getValueIsAdjusting() && !myPokemons.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					String type;
					Statement stmt = conn.createStatement();				// SQL 문장 만들기 위한 Statement 객체
					ResultSet rs = stmt.executeQuery("SELECT poke_name, height, weight, poke_gender_id, nickname, type, poke_character, book_picture, pokemon_id FROM pokemon "
							+ "NATURAL JOIN user_pokemon up NATURAL JOIN contact_type NATURAL JOIN type NATURAL JOIN poke_character c "
							+ "WHERE c.character_id = up.character_id AND poke_name = '" + myPokemons.getSelectedValue() + "' AND up.user_id = " + user_index.getText());
					rs.next();											// 여러개가 리턴되어도 첫번째 것으로 사용 
					type = rs.getString("type");
					book_name.setText(rs.getString("poke_name"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
					book_height.setText(rs.getString("height") + "m");		
					book_weight.setText(rs.getString("weight") + "kg");	
					if (rs.getString("poke_gender_id").equals("1"))			
						book_gender.setText("수컷");
					else
						book_gender.setText("암컷");
					book_nickname.setText(rs.getString("nickname"));

					book_character.setText(rs.getString("poke_character"));
					bookPoke = new ImageIcon(rs.getString("book_picture"));
					poke_index.setText(rs.getString("pokemon_id"));
					while(rs.next())
						type += ", " + rs.getString("type");
					book_type.setText(type);
					showBook = true;
					bookPanel.repaint();

					stmt.close();
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}	
	public class FeedListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {					// 리스트의 선택이 바뀔때마다 호출
			if (!lse.getValueIsAdjusting() && !Feed.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();				// SQL 문장 만들기 위한 Statement 객체
					ResultSet rs = stmt.executeQuery("SELECT r.re_id, phone, email, r.recommend, r.reply FROM user "
							+ "NATURAL JOIN recommend r where r.re_id = " + Feed.getSelectedValue() );
					rs.next();											// 여러개가 리턴되어도 첫번째 것으로 사용 
					fb_phone.setText(rs.getString("phone"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
					fb_email.setText(rs.getString("email"));		
					getFBText.setText(rs.getString("recommend"));	
					fbReply.setText(rs.getString("reply"));
					fb_index.setText(rs.getString("re_id"));
					stmt.close();
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}	
	public class ManagerPokeListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {					// 리스트의 선택이 바뀔때마다 호출
			if (!lse.getValueIsAdjusting() && !managerPoke.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();				// SQL 문장 만들기 위한 Statement 객체
					ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon p where p.poke_name = '" + managerPoke.getSelectedValue() +"'");
					rs.next();											// 여러개가 리턴되어도 첫번째 것으로 사용 
					mPokeName.setText(rs.getString("poke_name"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
					mPokeHeight.setText(rs.getString("height"));		
					mPokeWeight.setText(rs.getString("weight"));	
					mPokePicture.setText(rs.getString("picture"));
					mPokeBookPicture.setText(rs.getString("book_picture"));
					MPokeIndex.setText(rs.getString("pokemon_id"));
					stmt.close();
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}
	public class JoinFinishBtnListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if(check) {
				try {
					Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
					int gender_id;
					if (male.isSelected())
						gender_id = 1;
					else
						gender_id = 2;
					if(join_id.getText().isEmpty() || join_pw.getText().isEmpty()
							|| name.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()) {
						JOptionPane.showMessageDialog(newFrame, "빈 칸이 있어요...! T3T");
					}
					else {
						stmt.executeUpdate("INSERT INTO user (gender_id, id, pw, name, phone, email) VALUES (" +	// 새 레코드로 변경
								gender_id + ", '" +
								join_id.getText().trim() + "', '" +
								join_pw.getText().trim() + "', '" +
								name.getText().trim() + "', '" +
								phone .getText().trim() + "', '" +
								email.getText().trim() + "')");
						JOptionPane.showMessageDialog(newFrame, "회원가입을 축하합니다! ★");
						getContentPane().add("LoginPanel",loginPanel); 
						card.show(getContentPane(), "LoginPanel"); 
						join_id.setText("");
						join_pw.setText("");
						name.setText("");
						phone.setText("");
						email.setText("");
						male.setSelected(true);
						female.setSelected(false);
					}
					stmt.close();
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			else
				JOptionPane.showMessageDialog(newFrame, "아이디 중복 확인을 해주세요! ♥");
		}
	}
	public class IdCheckListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String query = "SELECT id FROM user";
			boolean idEmpty = false, idAlready = false, idPossible = false;
			try {
				Statement stmt = conn.createStatement();		
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					if(join_id.getText().trim().isEmpty()) {
						idEmpty = true;
					}
					else if(join_id.getText().trim().equals(rs.getString("id"))) {
						check = false;
						idAlready = true;
					}
					else {
						check = true;
						idPossible = true;
					}
				}
				if(idEmpty)
					JOptionPane.showMessageDialog(newFrame, "아이디를 입력해주세용♥");
				else if(idAlready)
					JOptionPane.showMessageDialog(newFrame, "이미 있는 아이디 입니다...! T3T");
				else if(idPossible)
					JOptionPane.showMessageDialog(newFrame, "사용 가능한 아이디 입니다!♥");
			}catch (SQLException sE){
				sE.printStackTrace();
			}
		}
	}
	public class LoginBtnListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String loginID = id.getText().trim();
			String loginPass = pw.getText().trim();
			String query = "SELECT * FROM user WHERE id ='" + loginID +"'";
			if(id.getText().isEmpty() || pw.getText().isEmpty()) {
				JOptionPane.showMessageDialog(newFrame, "아이디와 비밀번호 모두 입력해주세요...! T3T");
			}
			else if(loginPass.equals("5098")) {
				getContentPane().add("ManagerChoicePanel", mChoicePanel); 
				card.show(getContentPane(), "ManagerChoicePanel"); 
				user= false;
				prepareList();
			}
			else{
				try {
					Statement stmt = conn.createStatement();		
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
						if(loginPass.equals(rs.getString("pw"))) {
							getContentPane().add("ChoicePanel",choicePanel); 
							card.show(getContentPane(), "ChoicePanel"); 
							info_id.setText(rs.getString("id"));
							info_pw.setText(rs.getString("pw"));
							if(male.isSelected())
								info_male.setSelected(true);
							else
								info_female.setSelected(false);
							info_name.setText(rs.getString("name"));
							info_phone.setText(rs.getString("phone"));
							info_email.setText(rs.getString("email"));
							user_index.setText(rs.getString("user_id"));
							id.setText("");
							pw.setText("");
							user= true;
							prepareList();
						}
						else {
							JOptionPane.showMessageDialog(newFrame, "아이디와 비밀번호가 맞지 않아요...! ㅇㅁㅇ!");
						}
						stmt.close();
					}
					else
						JOptionPane.showMessageDialog(newFrame, "존재하지 않는 아이디 입니다...! ㅇㅁㅇ!");
				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}
	public class NewPokeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			int poke = (int)(Math.random()*9)+1;
			int gender_id = (int)(Math.random()*2)+1;
			int character_id = (int)(Math.random()*36)+1;
			String query = "SELECT * FROM pokemon WHERE pokemon_id ='" + poke +"'";
			String query2 = "SELECT * FROM user_pokemon WHERE pokemon_id ='" + poke +"' AND user_id = " + user_index.getText();
			try {
				Statement stmt = conn.createStatement();	
				ResultSet rs2 = stmt.executeQuery(query2);
				if(rs2.next())
					JOptionPane.showMessageDialog(newFrame, "이미 가지고 있는 아이가 나왔어요! 다시 뽑아주세요!");
				else {
				ResultSet rs = stmt.executeQuery(query);
				prepareList();
				if(rs.next()) {
					newPoke = new ImageIcon(rs.getString("picture"));
					poke_name.setText(rs.getString("poke_name"));
					poke_index.setText(rs.getString("pokemon_id"));
					getContentPane().add("NewFriendPanel",newFriendPanel); 
					card.show(getContentPane(), "NewFriendPanel");
					stmt.executeUpdate("INSERT INTO user_pokemon (user_id, pokemon_id, character_id, poke_gender_id) VALUES (" +	// 새 레코드로 변경
							user_index.getText().trim() + ", " +
							poke_index.getText().trim() + ", " +
							character_id + ", " +
							gender_id + ")");
				}
					stmt.close();
				}
			}
			catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class NickFinishBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			try {
				if(nickname.getText().isEmpty()) {
					JOptionPane.showMessageDialog(newFrame, "별명을 입력해주세요! ♥");
				}
				else {
					Statement stmt = conn.createStatement();		
					stmt.executeUpdate("UPDATE user_pokemon SET nickname = '" +	// 새 레코드로 변경
							nickname.getText().trim() + "' WHERE pokemon_id = " + poke_index.getText().trim());
					JOptionPane.showMessageDialog(newFrame, "도감에 추가되었습니다!♥");
					getContentPane().add("GamePanel",gamePanel); 
					card.show(getContentPane(), "GamePanel"); 
					nickname.setText("");
					stmt.close();
				}
			}
			catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class InfoFinishBtnListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				int gender_id;
				if (info_male.isSelected())
					gender_id = 1;
				else
					gender_id = 2;
				stmt.executeUpdate("UPDATE user SET "  + 
						"gender_id = " + gender_id + ", id = '" + info_id.getText().trim() + "', pw  = '" +
						info_pw.getText().trim() + "', name = '" + info_name.getText().trim() + "', email = '" +
						info_email.getText().trim() + "', phone = '" + info_phone.getText().trim() + 
						"' WHERE user_id = " + user_index.getText().trim());
				JOptionPane.showMessageDialog(newFrame, "정보수정이 완료되었습니다! ★");
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class DropIDBtnListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("DELETE FROM user_pokemon WHERE user_id = " + user_index.getText().trim());
				stmt.executeUpdate("DELETE FROM recommend WHERE user_id = " + user_index.getText().trim());
				stmt.executeUpdate("DELETE FROM user WHERE user_id = " + user_index.getText().trim());
				JOptionPane.showMessageDialog(newFrame, "꼭 다시 돌아와야 해요...8ㅅ8");
				getContentPane().add("LoginPanel",loginPanel); 
				card.show(getContentPane(), "LoginPanel"); 
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class fbSaveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("INSERT INTO recommend (user_id, recommend) VALUES (" +	// 새 레코드로 변경
						user_index.getText().trim() + ", '" +
						fbText.getText() + "')");
				JOptionPane.showMessageDialog(newFrame, "개발자에게 전송되었습니다!");
				fbText.setText("");
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class ReleaseButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("DELETE FROM user_pokemon WHERE pokemon_id = " +
						poke_index.getText().trim() + " AND user_id = " + user_index.getText().trim());
				stmt.close();
				JOptionPane.showMessageDialog(newFrame, "자연으로 돌아갔어요...!");
				prepareList();											// 리스트 박스 새 리스트로 다시 채움 
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class UpNickBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();		
				stmt.executeUpdate("UPDATE user_pokemon SET nickname = '" +	// 새 레코드로 변경
						book_nickname.getText().trim() + "' WHERE pokemon_id = " + poke_index.getText().trim());
				JOptionPane.showMessageDialog(newFrame, "수정 완료!♥");
				prepareList();
				stmt.close();
			}
			catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class MPokeSaveBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String mpoke_index = MPokeIndex.getText().trim();
			String query = "SELECT * FROM pokemon WHERE pokemon_id = " + mpoke_index;
			try {
				Statement stmt = conn.createStatement();	
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()) {
					stmt.executeUpdate("UPDATE pokemon SET "  + 
							"poke_name = '" + mPokeName.getText().trim() + "', height = '" + mPokeHeight.getText().trim() + 
							"', weight  = '" + mPokeWeight.getText().trim() + "', book_picture = '" + mPokeBookPicture.getText().trim() + 
							"', picture = '" + mPokePicture.getText().trim() + "' WHERE pokemon_id = " + mpoke_index);
					JOptionPane.showMessageDialog(newFrame, "수정되었습니다.");
					prepareList();
				}else {
					stmt.executeUpdate("INSERT INTO pokemon (poke_name, height, weight, book_picture, picture) VALUES ( '" +	// 새 레코드로 변경
							mPokeName.getText().trim() + "' , '" +
							mPokeHeight.getText().trim() + "', '" +
							mPokeWeight.getText().trim() + "', '" +
							mPokeBookPicture.getText().trim() + "', '" +
							mPokePicture.getText().trim() + "')");
					JOptionPane.showMessageDialog(newFrame, "저장되었습니다.");
					prepareList();
				}
				
				stmt.close();
			}
			catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}public class DropPokeListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("DELETE FROM user_pokemon WHERE pokemon_id = " + MPokeIndex.getText().trim());
				stmt.executeUpdate("DELETE FROM contact_type WHERE pokemon_id = " + MPokeIndex.getText().trim());
				stmt.executeUpdate("DELETE FROM pokemon WHERE pokemon_id = " + MPokeIndex.getText().trim());
				JOptionPane.showMessageDialog(newFrame, "삭제되었습니다.");
				prepareList();
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class fbFinishListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("UPDATE recommend SET reply = '" +	// 새 레코드로 변경
						fbReply.getText().trim() + "' where re_id = " + fb_index.getText().trim());
				JOptionPane.showMessageDialog(newFrame, "저장되었습니다.");
				fbReply.setText("");
				prepareList();
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class ManagerPokemonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				getContentPane().add("ManagerPokePanel",mPokePanel); 
				card.show(getContentPane(), "ManagerPokePanel");
				stmt.close();
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class PokemonDisplayButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// DB에서 가져오는 데이터를 rowObjects의 형태로 저장하고 이들의 리스트를 Printer 또는 Preview로 보내 줌
			ArrayList<RowObjects> rowList = new ArrayList<RowObjects>();	// 행들의 리스트
			RowObjects line;												// 하나의 행
			PrintObject word;												// 하나의 단어
			try {
				Statement stmt = conn.createStatement();					// SQL 문장 만들기 위한 Statement 객체
				ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon");
				while(rs.next()) {
					line = new RowObjects();								// 5개의 단어가 1줄
					line.add(new PrintObject(rs.getString("pokemon_id"), 20));
					line.add(new PrintObject(rs.getString("poke_name"), 20));
					line.add(new PrintObject(rs.getString("height"), 20));
					line.add(new PrintObject(rs.getString("weight"), 5));
					rowList.add(line);										// 출력해야 될 전체 리스트를 만듬									
				}
				stmt.close();
				// 각 페이지의 칼럼 헤더를 위해 한 줄 만들음
				line = new RowObjects();									// 5개의 단어가 1줄
				line.add(new PrintObject("번호", 20));
				line.add(new PrintObject("이름", 20));
				line.add(new PrintObject("키(m)", 20));
				line.add(new PrintObject("몸무게(kg)", 5));

				if (e.getSource() == mPokePrintBtn) {
	    			Printer prt = new Printer(new PrintObject("포켓몬리스트", 20), line, rowList, true);
	    			prt.print();
    			}
    			else {
	    			Preview prvP = new Preview(new PrintObject("포켓몬리스트", 20), line, rowList, true);
	    			prvP.preview();
    			}

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class UserDisplayButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// DB에서 가져오는 데이터를 rowObjects의 형태로 저장하고 이들의 리스트를 Printer 또는 Preview로 보내 줌
			ArrayList<RowObjects> rowList = new ArrayList<RowObjects>();	// 행들의 리스트
			RowObjects line;												// 하나의 행
			PrintObject word;												// 하나의 단어
			try {
				Statement stmt = conn.createStatement();					// SQL 문장 만들기 위한 Statement 객체
				ResultSet rs = stmt.executeQuery("SELECT * FROM user");
				while(rs.next()) {
					line = new RowObjects();								// 5개의 단어가 1줄
					line.add(new PrintObject(rs.getString("user_id"), 20));
					line.add(new PrintObject(rs.getString("id"), 20));
					line.add(new PrintObject(rs.getString("pw"), 20));
					line.add(new PrintObject(rs.getString("name"), 20));
					if(rs.getString("gender_id").equals(1))
						line.add(new PrintObject("남성", 20));
					else
						line.add(new PrintObject("여성", 20));
					line.add(new PrintObject(rs.getString("phone"), 30));
					line.add(new PrintObject(rs.getString("email"), 30));
					rowList.add(line);										// 출력해야 될 전체 리스트를 만듬									
				}
				stmt.close();
				// 각 페이지의 칼럼 헤더를 위해 한 줄 만들음
				line = new RowObjects();									// 5개의 단어가 1줄
				line.add(new PrintObject("번호", 20));
				line.add(new PrintObject("아이디", 20));
				line.add(new PrintObject("비밀번호", 20));
				line.add(new PrintObject("이름", 20));
				line.add(new PrintObject("성별", 20));
				line.add(new PrintObject("핸드폰", 30));
				line.add(new PrintObject("이메일", 30));

				Preview prvU = new Preview(new PrintObject("유저리스트", 20), line, rowList, true);
				prvU.preview();

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startBtn) {
				getContentPane().add("LoginPanel",loginPanel); 
				card.show(getContentPane(), "LoginPanel"); 
			}
			if(e.getSource() == bookReturnBtn || e.getSource() == gameReturnBtn || e.getSource() == infoReturnBtn) {
				getContentPane().add("ChoicePanel",choicePanel); 
				card.show(getContentPane(), "ChoicePanel"); 
			}
			if(e.getSource() == joinBtn) {
				getContentPane().add("JoinPanel",joinPanel); 
				card.show(getContentPane(), "JoinPanel"); 
			}
			if(e.getSource() == friendsBtn) {
				getContentPane().add("GamePanel",gamePanel); 
				card.show(getContentPane(), "GamePanel"); 
			}
			if(e.getSource() == choiceBookBtn) {
				prepareList();
				getContentPane().add("BookPanel",bookPanel); 
				card.show(getContentPane(), "BookPanel"); 
			}
			if(e.getSource() == infoBtn || e.getSource() == fbReturnBtn) {
				getContentPane().add("InfoPanel",infoPanel); 
				card.show(getContentPane(), "InfoPanel"); 
			}
			if(e.getSource() == FeedbackBtn) {
				getContentPane().add("FeedbackPanel",feedbackPanel); 
				card.show(getContentPane(), "FeedbackPanel"); 
			}
			if(e.getSource() == mFeedbackBtn) {
				getContentPane().add("ManagerFeedbackPanel",mFeedPanel); 
				card.show(getContentPane(), "ManagerFeedbackPanel"); 
			}
			if(e.getSource() == mReturnBtn || e.getSource() == mPokeReBtn) {
				getContentPane().add("ManagerChoicePanel", mChoicePanel); 
				card.show(getContentPane(), "ManagerChoicePanel"); 
			}
			if(e.getSource() == mSearchPokeBtn) {
				int index = managerPoke.getNextMatch(mPokeSearch.getText().trim(), 0, Position.Bias.Forward);
				System.out.println(index);
				if (index != -1) {
					System.out.println(123);
					managerPoke.setSelectedIndex(index);
				}
				mPokeSearch.setText("");
			}
			if(e.getSource() == mPokeNewBtn) {
				MPokeIndex.setText("0");
				mPokeName.setText("");		
				mPokeHeight.setText("");
				mPokeWeight.setText("");
				mPokePicture.setText("");
				mPokeBookPicture.setText("");
			}
			if(e.getSource() == choiceEndBtn || e.getSource() == mEndBtn) {
				dispose();
			}
		}
	}
}
