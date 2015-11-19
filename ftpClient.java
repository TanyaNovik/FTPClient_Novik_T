import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;
public class ftpClient {
	
	FTPClient client = new FTPClient();
	Scanner sc = new Scanner(System.in);
	static final int port = 21; 
	public void menu() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException{
		
		
		String flag = "1";
		while(flag.equals("1"))
		{
			System.out.println("1. connect");
			System.out.println("2. next or download");
			System.out.println("3. back");
			System.out.println("4. dir content");
			System.out.println("If you want exit, enter 0.");
			System.out.println("Enter command: ");
			String command = sc.nextLine();
			switch(command)
			{
			case "1":
			{
				connect();
				break;
			}
			case "2":
			{
				if(client.getHost() != null)
				{
				System.out.println("Enter name file or folder: ");
				String file_folder = sc.next();
				FolderOrFile(file_folder);
				}
				else System.out.println("You must first connect. Enter 1!!!!!");
				break;
			}
			case "3":
			{
				if(client.getHost() != null)
				{
				back();
				}
				else System.out.println("You must first connect. Enter 1!!!!!");
				break;
			}
			case "4":
			{
				if(client.getHost() != null)
				{
				printContent();
				}
				else System.out.println("You must first connect. Enter 1!!!!!");
				break;
			}
			case "0":
			{
				flag = "0";
				break;
			}
			default:
			{
				break;
			}
			
			
		}
		
		
	}
		if(client.getHost() != null)
		{
			client.disconnect(true);
		}
		
	}
	
	

	public void connect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
	{
		if(client.getHost() == null)
		{
		System.out.println("Enter connect url: ");
		String connect = sc.next();
		System.out.println("Enter login: ");
		String login = sc.next();
		System.out.println("Enter password: ");
		String password = sc.next();
		client.connect(connect);
		client.login(login, password);
		}
		else System.out.println("Connection has been established!!!");
	}
	
	public void printContent() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException
	{
		FTPFile[] files = null;
		files = client.list();
		for(FTPFile file : files)
		{
			System.out.println(file.getName());
		}
	}
	
	public void next(String dir) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException
	{
		client.changeDirectory(dir);
	}
	private void back() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
		client.changeDirectoryUp();
	}
	
	public void downloadFile(String downFile) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException
	{
		File localFile = new File("D:\\" + downFile);
		client.download(downFile, localFile);
		
	}
	
	public void FolderOrFile(String file_folder) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException
	{
		FTPFile[] files = null;
		files = client.list();
		for(FTPFile file : files)
		{
			if(file.getName().equals(file_folder))
			{
				System.out.println(file.getType());
				if(file.getType() == 0)
				{
					downloadFile(file_folder);
				}
				if(file.getType() == 1)
				{
					next(file_folder);
				}
			}
		}
		
	}
}

	
	
	
	
	
		
		
	
				
	
