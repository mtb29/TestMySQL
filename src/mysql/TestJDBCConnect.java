package mysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Test connection to MySQL. Be sure to use a valid mysql username and the ip
 * address of your server in the getConnection line. Set your AWS firewall to
 * allow mysql port 3306 connections from the ip address of the Windows machine
 * you are using. If you are using an AWS Windows machine then you will need to
 * open your AWS control panel there to allow the ip address of that machine.
 *
 * @author John Phillips
 */
public class TestJDBCConnect {

    public static void main(String[] args) {
        //try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false","yourdbusername","yourdbuserpassword")){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://54.197.22.56:3306/?useSSL=false", "jdoe", "mucis")) {
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Here are the steps I did to accept remote connections from a Windows computer.
// I assume the server was setup with https://github.com/profphillips/awssetup d1SetupDevServerAws.sh
// 1. I got the program working with localhost on the server itself. If you are using my github AWS server install script then the JDBC driver is already installed.
// 2. Opened AWS control panel security settings and added port 3306 for my Windows computer's ip address. (If AWS Windows then use that ip address!)
// 3. Edited server's /etc/mysql/mysql.conf.d/mysqld.cnf and commented out # bind-address = 127.0.0.1
// 4. Restart the mysql server using: sudo service mysql restart 
// 5. Logged into mysql using:  mysql -u root -p and gave the following command for user jdoe:
// mysql> grant all on *.* to 'jdoe'@'%' identified by 'mucis';
// 6. I modified this program's getConnection method as shown above.
// 7. Download and unzipped the MySQL JDBC driver and pulled the .jar file out into the same directory as this program.
//
// For connecting from NetBeans
// 1. Copied the MySQL JDBC .jar file to the src/mysql folder for this project 
// 2. Right-clicked on Libraries and Add Jar/Folder and chose the .jar file
//
// For connecting from the Windows Command Prompt
// 1. Compiled program with: javac JDBCTest.java
// 2. Ran program with:
// C:\mypathtofiles>java -cp .;mysql-connector-java-5.1.38-bin.jar JDBCTest
