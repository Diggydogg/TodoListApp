package com.todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		
		TodoUtil.loadList(l, "todolist.txt");
		
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			//tokenizer 
			String command = sc.nextLine();
			
			StringTokenizer st= new StringTokenizer(command," ");
			String choice = st.nextToken().trim();
			//System.out.println(choice);
			//String choice = sc.next();
			
			
			switch (choice) {

			case "add":
				System.out.println("항목 추가 진행 >>>");
				TodoUtil.createItem(l);
				break;
			
			case "del":
				System.out.println("항목 삭제 진행 >>>");
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				System.out.println("항목 수정 진행 >>>");
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				System.out.println("항목 전체 보기 진행 >>>");
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("이름순 정렬 진행 >>>");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("이름별 역정렬 진행 >>>");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("날짜별 정렬 진행 >>>");
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				System.out.println("시스템 종료 >>>");
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				Menu.prompt();
				break;
				
			case "find":
				String keyword = st.nextToken();
				TodoUtil.find(l,keyword);
				break;
				
			default:
				System.out.println("please enter one of the above mentioned command");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
