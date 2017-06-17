package wyykt.gloria.practice.castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	private class Handler {
		
		public void doCmd(String word){
			
		}
		
		public boolean isBye(){
			return false;
		}
		
	}
	
	private class HandlerBye extends Handler {
		
		public boolean isBye(){
			return true;
		}
		
	}
	
	private class HandlerGo extends Handler {
		
		@Override
		public void doCmd(String word) {
			goRoom(word);
		}
	}
	
	private class HandlerHelp extends Handler {
		
		@Override
		public void doCmd(String word) {
			System.out.println("迷路了吗？你可以做的命令有：go do bye help");
			System.out.println("如：\tgo east");
			System.out.println("\tdo game");
		}
		
	}
	
	private class HandlerDo extends Handler {
		
		@Override
		public void doCmd(String word) {
			doSomething(word);
		}
		
	}
	
	
	private Room currentRoom;
	private HashMap<String, Handler> handlers = new HashMap<String, Handler>();

	public Game() {
		handlers.put("go", new HandlerGo());
		handlers.put("bye", new HandlerBye());
		handlers.put("help", new HandlerHelp());
		handlers.put("do", new HandlerDo());
		createRooms();
	}

	private void createRooms() {
		Room outside, lobby, pub, study, bedroom, exercise;
		
		// 可以干的事
		Specialty drink = new Specialty("drink", "喝水", "嗯~~ 好喝！！");
		Specialty beer = new Specialty("beer", "喝啤酒", "来来来，干杯！");
		Specialty book = new Specialty("book", "看书", "这本书不错，带回家好好看。");
		Specialty bed = new Specialty("bed", "睡觉" ,"睡了一会觉，精力很充沛。");
		Specialty ecise = new Specialty("exercise", "跑步", "速度七十迈，心情是自由自在，希望终点是爱琴海，全力奔跑梦在彼岸");
		Specialty game = new Specialty("game", "玩手游", "哈哈，又升了一级，哦耶！！");

		// 制造房间
		outside = new Room("城堡外");
		outside.setSomth(game);
		outside.setSomth(ecise);
		lobby = new Room("大堂");
		lobby.setSomth(book);
		pub = new Room("小酒吧");
		pub.setSomth(beer);
		pub.setSomth(bed);
		study = new Room("书房");
		study.setSomth(book);
		study.setSomth(drink);
		study.setSomth(game);
		bedroom = new Room("卧室");
		bedroom.setSomth(bed);
		bedroom.setSomth(beer);
		bedroom.setSomth(game);
		exercise = new Room("健身房");
		exercise.setSomth(ecise);
		exercise.setSomth(drink);
		exercise.setSomth(game);

		// 初始化房间的出口
		outside.setExit("east", lobby);
		outside.setExit("south", study);
		outside.setExit("west", pub);
		lobby.setExit("west", outside);
		pub.setExit("east", outside);
		study.setExit("north", outside);
		study.setExit("east", bedroom);
		bedroom.setExit("west", study);

		study.setExit("up", exercise);
		exercise.setExit("down", study);

		currentRoom = outside; // 从城堡门外开始
	}

	private void printWelcome() {
		System.out.println();
		System.out.println("欢迎来到城堡！");
		System.out.println("这是一个超级无聊的游戏。");
		System.out.println("如果需要帮助，请输入 'help' 。");
		System.out.println();
		printPrompt();
	}

	private void printPrompt() {
		System.out.println("你在" + currentRoom);
		System.out.print("出口有：");
		System.out.print(currentRoom.getExitDesc());
		
		if(currentRoom.hasSomethingToDo()){
			System.out.print("  你可以：");
			System.out.print(currentRoom.getSomthdoDesc());
		}
		
		System.out.println();
	}
	
	
	
	public void doSomething(String what){
		Specialty s = currentRoom.getSpecialty(what);
		
		if(s == null){
			System.out.println("在这里做不了");
		} else {
			System.out.println(s.getEffect());
		}
		
		
	}


	public void goRoom(String direction) {
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("那里没有门！");
		} else {
			currentRoom = nextRoom;
			printPrompt();
		}
	}


	public void play() {
		Scanner in = new Scanner(System.in);

		while (true) {
			String line = in.nextLine();
			String[] words = line.split(" ");
			Handler handler = handlers.get(words[0]);
			String value = "";
			if(words.length > 1){
				value = words[1];
			}
			if(handler != null){
				handler.doCmd(value);
				if(handler.isBye()){
					break;
				}
			}
			
		}

		in.close();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();

		System.out.println("感谢您的光临。再见！");
	}

}
