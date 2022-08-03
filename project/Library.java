package project;
import java.util.*;
import java.io.*;
class Book implements Serializable{
	private static final long serialVersionUID=3L;
	String name;
	String author;
	int copies;
	Book(String n,String a,int c){
	name=n;
	author=a;
	copies=c;
	}
	
	static ArrayList<Book> l=new ArrayList<Book>();
	
	static void getBook() {
		try {
		FileInputStream m=new FileInputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\book.txt");
		ObjectInputStream mo=new ObjectInputStream(m);
		l=(ArrayList<Book>)mo.readObject();
		mo.close();
		}
		catch(EOFException m) {
			
		}
		catch(Exception e) {
			System.out.println("Exception found : "+ e);
			
		}
	}
	
	static void addBook(Book w1) {

		try {
			Iterator<Book> itr= l.iterator();
			while(itr.hasNext()) {
			   Book  s=itr.next();
				if(s.name.equals(w1.name)&&s.author.equals(w1.author)) {
					w1.copies=s.copies+w1.copies;
					l.remove(s);
					break;
					}
			}
		FileOutputStream v=new FileOutputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\book.txt");
		ObjectOutputStream vi=new ObjectOutputStream(v);
		l.add(w1);
		vi.writeObject(l);
		vi.flush();
		vi.close();
		}
		catch(Exception e) {
			System.out.println("//THERE IS AN EXCEPTION //"+e);
			}
		System.out.println("Book Added");
		}
			
	static void displayBooks(){
		Iterator<Book> itr= l.iterator();
		while(itr.hasNext()) {
		Book s=itr.next();
		if(s.copies>0)
		System.out.println(s.name+"\t"+s.author+"\t"+s.copies);
	}
    }
	
	static int change_copy(String autho,String name,int a) {
		Iterator<Book> itr= l.iterator();
		//Book s=itr.next();
		int copies=0;
		int flag=0;
		if(a==0) {
		while(itr.hasNext()) {
		   Book  s=itr.next();
			if(s.name.equals(name)&&s.author.equals(autho)) {
				flag=1;
				copies=s.copies+1;
				l.remove(s);
				break;
			}
	      }
		}
		if(a==1){
			while(itr.hasNext()) {
			  Book  s=itr.next();
				if(s.name.equals(name)&&s.author.equals(autho)) {
					flag=1;
					copies=s.copies-1;
					l.remove(s);
					break;
				}
		      }
			}
		if(flag==0)
			return 0;
		Book b=new Book(name,autho,copies);
		l.add(b);
		try {
			FileOutputStream v=new FileOutputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\book.txt");
		ObjectOutputStream vi=new ObjectOutputStream(v);
		//l.add(w1);
		vi.writeObject(l);
		vi.flush();
		vi.close();
		}
		catch(Exception e) {
			System.out.println("//THERE IS AN EXCEPTION //"+e);
			}
		return 1;
		
	}
}

class Borrower implements Serializable{
	private static final long serialVersionUID=1L;
	String name;
	long mobyl;
	String book;
	String auth;
	int borrow_date;
	Date d=new Date();
	int return_date;
	Borrower(String n,long nf,String m,String au){
	name=n;
	mobyl=nf;
	book=m;
	auth=au;
	borrow_date=d.getDate();
	return_date=50;
	}
	static ArrayList<Borrower> ll=new ArrayList<Borrower>();
	static void addBorrow(Borrower b1) {
		try {
		FileOutputStream v=new FileOutputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\borrower.txt");
		ObjectOutputStream vi=new ObjectOutputStream(v);
		ll.add(b1);
		vi.writeObject(ll);
		vi.flush();
		vi.close();
		}
		catch(Exception e) {
			System.out.println("//THERE IS AN EXCEPTION //"+e);
			}
	}
	
	static void getBorrower() {
		try {
		FileInputStream m=new FileInputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\borrower.txt");
		ObjectInputStream mo=new ObjectInputStream(m);
		ll=(ArrayList<Borrower>)mo.readObject();
		mo.close();
		
		}
		catch(EOFException m) {}
		catch(Exception e) {
			System.out.println("Exception found : "+ e);
			
		}
	}
	
	static void displayBorrower(){
		Iterator<Borrower> itr= ll.iterator();
		while(itr.hasNext()) {
		Borrower s=itr.next();	
		System.out.println(s.name+"\t"+s.mobyl+"\t"+s.book+"\t"+s.auth+"\t"+s.borrow_date);
	}
    }
	static void remove(String n,String m,String b,long k ) {
		Iterator<Borrower> itr= ll.iterator();
		//Borrower s=itr.next();
		while(itr.hasNext()) {
		   Borrower  s=itr.next();
			if(s.mobyl==k&&s.book.equals(n)&&s.name.equals(b)&&s.auth.equals(m)) {
		    ll.remove(s);
		    break;
		    }
		}
		try {
			FileOutputStream v=new FileOutputStream("C:\\Users\\vinay\\eclipse-workspace\\VINAY\\src\\project\\borrower.txt");
			ObjectOutputStream vi=new ObjectOutputStream(v);
			
			vi.writeObject(ll);
			vi.flush();
			vi.close();
			}
			catch(Exception e) {
				System.out.println("//THERE IS AN EXCEPTION //"+e);
				}
		int bl=Book.change_copy(m,n,0);
		if(bl==0)
			System.out.println("No such Borrower exists");
		else
			System.out.println("Updated");
			
      }
	
}

public class Library {
public static void main (String[] args) {
	int choice,c;
	String n,m,s;
	long f;
	Book.getBook();
	Borrower.getBorrower();
	Scanner scan=new Scanner(System.in);
	System.out.println("----->  LIBRARY MANAGEMENT SYSTEM  <------");
	System.out.println(" ENTER YOUR CHOICE : ");
	System.out.println(" 1.LIST OF BOOKS ");
	System.out.println(" 2.LIST OF BORROWED BOOKS");
	System.out.println(" 3.ADD BOOKS TO THE SHELF ");
	System.out.println(" 4.TO ENTER DETAILS OF BORROWER ");
	System.out.println(" 5.RETRIEVAL OF A BOOK ");
	choice=scan.nextInt();
	while(choice>0&&choice<6) {
	if(choice==1) {
		System.out.println("Books available :\n");
		Book.displayBooks();
	}
	if(choice==2) {
		System.out.println("Borrowers : \n");
		Borrower.displayBorrower();
	}
	if(choice==3) {
		String g;
		System.out.println("ENTER THE NAME OF THE BOOK : ");
		g=scan.nextLine();
		n=scan.nextLine();
		System.out.println("ENTER THE NAME OF THE AUTHOR : ");
		m=scan.nextLine();
		System.out.println("ENTER THE NUMBER OF COPIES : ");
		c=scan.nextInt();
		Book e1=new Book(n,m,c);
		Book.addBook(e1);
	}
	if(choice==4) {
	 String k,k1;
	 System.out.println("ENTER THE NAME OF THE PERSON :");
	 k=scan.nextLine();
	 n=scan.nextLine();
	 System.out.println("ENTER THE MOBILE NUMBER : ");
	 f=scan.nextLong();
	 System.out.println("ENTER THE NAME OF THE BOOK : ");
	 k1=scan.nextLine();
	 m=scan.nextLine();
	 System.out.println("ENTER THE AUTHOR NAME");
	 s=scan.nextLine();
	 Borrower b=new Borrower(n,f,m,s);
	 int bk=Book.change_copy(s, m, 1);
	 if(bk==1) {
	 Borrower.addBorrow(b);
	 System.out.println("Details of the borrower added");
	 }
	 else
		 System.out.println("Sorry _Book does not exist");
	}
	if(choice==5) {
		String k,K;
		System.out.println("ENTER THE BOOK NAME : ");
		k=scan.nextLine();
		n=scan.nextLine();
		System.out.println("ENTER THE AUTHOR NAME : ");
		m=scan.nextLine();
		System.out.println("ENTER THE NAME OF THE BORROWER :");
		K=scan.nextLine();
		System.out.println("ENTER THE MOBILE NUMBER");
		f=scan.nextLong();
		Borrower.remove(n, m, K, f);
		
	}
	System.out.println("\n\n\n ENTER YOUR CHOICE : ");
	System.out.println(" 1.LIST OF BOOKS ");
	System.out.println(" 2.LIST OF BORROWED BOOKS");
	System.out.println(" 3.ADD BOOKS TO THE SHELF ");
	System.out.println(" 4.TO ENTER DETAILS OF BORROWER ");
	System.out.println(" 5.RETRIEVAL OF A BOOK ");
	choice=scan.nextInt();
	}
	System.out.println("\t\t*******\t");
}
}
