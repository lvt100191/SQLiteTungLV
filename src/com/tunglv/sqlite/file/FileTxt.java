/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.file;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TUNGLV
 */
//doc file text lay ra danh sách email
public class FileTxt {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String from ="lazada.ohaythe@gmail.com";
        String pwd ="123456a@";
        String title="Chuột quang không dây Forter V189 V111 + Tặng miếng lót chuột  ";
        String content ="http://bit.ly/2hyREyh "
                    + "Là sản phẩm cách tân vừa ra mắt năm 2014 hỗ trợ một cách hoàn hảo công việc văn phòng của bạn, chuột không dây Forter V189 có thiết kể trẻ trung, trang nhã và độ phân giải cao đến 1500dpi. Điểm nổi bật nhất của sản phẩm là khả năng tự động tắt, bật khi kết nối hoặc shutdown máy tính. Đây sẽ là người bạn hỗ trợ đắc lực cho mọi công việc trên máy tính của bạn. Tiếp nối những tính năng nổi trội của các sản phẩm trước, Forter V189 được cải tiến và nâng cấp đáp ứng nhu cầu làm việc với cường độ và áp lực cao.\n"
                    + "\n"
                    + "TÍNH NĂNG NỔI BẬT\n"
                    + "Thiết kế Ergonomic, 3 phím chức năng\n"
                    + "Nhờ vào thiết kế Ergonomic thông minh, chuột không dây Forter V189 tạo sự thoải mái cho cả người thuận tay phải lẫn tay trái. Sản phẩm được trang bị 3 phím chức năng cơ bản giúp người dùng dễ dàng thao tác, có cảm giác chắc chắn khi di chuyển chuột với cường độ nhanh.\n"
                    + "\n"
                    + "Độ phân giải 1500dpi, khoảng cách kết nối 15m\n"
                    + "Sản phẩm có độ phân giải cao lên đến 1500dpi giúp bạn thao tác nhanh, nhạy trong phạm vi 15m. Vậy nên, dù phải di chuyển liên tục trong văn phòng, bạn vẫn có thể thực hiện công việc trên máy tính một cách nhanh chóng, chính xác.\n"
                    + "\n"
                    + "Tự động bật, tắt & tiết kiệm năng lượng\n"
                    + "Với công nghệ tiên tiến, Forter V189 có khả năng tự động bật, tắt khi máy tính khởi động hoặc shutdown. Điều này giúp tiết kiệm năng lượng tối đa, cao hơn gấp 3 lần so với sản phẩm thông thường trên thị trường. Ngoài ra, sản phẩm được trang bị Pin 1AA Ultra Alkaline có tuổi thọ lên đến 24 tháng.\n"
                    + "\n"
                    + "THÔNG TIN SẢN PHẨM\n"
                    + "Màu: đen\n"
                    + "Trọng lượng (KG): 0.1\n"
                    + "Kích thước sản phẩm (D x R x C cm): 10x3x2\n"
                    + "Bảo hành: 24 tháng- Theo đúng tiêu chuẩn của Nhà sản xuất";
        ArrayList<String> lst = getListMail();
        for(String to: lst){
            try{
                sendEmail(from, pwd, to, title, content);
                System.out.println("tunglv gui toi mail"+to+" thanh cong");
            }catch(Exception e){
                System.out.println("tunglv gui toi mail: "+to+" bi loi"+e.getMessage()); 
            }
        }
    }

    public static ArrayList<String> getListMail() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("mail.txt"));
        FileOutputStream outputStream = new FileOutputStream("MailExtract.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        String mail = "";
        ArrayList<String> list = new ArrayList<String>();
        try {
            String line = br.readLine();
            while (line != null) {
                if (line.contains("@")) {
                    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line);
                    while (m.find()) {
                        mail = m.group();
                        char end = mail.charAt(mail.length() - 1);
                        if (end == '.') {
                            mail = mail.substring(0, mail.length() - 1);
                            bufferedWriter.write(mail + ";" + "\n");
                        } else {
                            bufferedWriter.write(mail + ";" + "\n");
                        }
                    }
                    list.add(mail);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            br.close();
            bufferedWriter.close();
        }
        return list;
    }

    //mailSend: email gui
    //passwordMailSend: mat khau cua email gui
    //mailRecipient: dia chi email nhan
    //title: tieu de mail
    //content: noi dung mail
    public static void sendEmail(String mailSend, String passwordMailSend, String mailRecipient, String title, String content) throws MessagingException {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    //chu y neu gap loi nay thi phai bat bao mat tren tai khoan mail google cho phep ung dung it bao mat hon Erreur d'envoi, cause: javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at
                    return new PasswordAuthentication(mailSend, passwordMailSend);
                }
            });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(mailSend));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailRecipient, false));
            msg.setSubject(title);
            msg.setText(content);
            msg.setSentDate(new Date());
            Transport.send(msg);
            //System.out.println("Message sent.");
        } catch (MessagingException e) {
            throw new MessagingException(e.getMessage());
        }
    }
}
