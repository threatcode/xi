package edu.utils.captchamake;


import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

public class CaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 2988061537143222797L;
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        int width = 300;
	        int height = 40;
	        int fontSize = 26;
	        int xGap = 30 ;
	        int yGap = 25 ;
	        String fontName = "Arial" ;
	        Color gradiantStartColor = new Color(225, 230, 240); // dark grey
	        Color gradiantEndColor = new Color(225, 230, 240); // light grey
	        Color textColor =  new Color(128, 22, 32); // orange

	        //String[] newData = {"hiworld", "orlando", "global", "publish", "looky"}; // you add more words or read them from db or something...
	        String[] newData=RandomGenerator.generateRandomWords(8);

	        BufferedImage bufferedImage = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);

	        Graphics2D g2d = bufferedImage.createGraphics();

	        RenderingHints rh = new RenderingHints(
	                RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.setRenderingHints(rh);

	        GradientPaint gp = new GradientPaint(0, 0, gradiantStartColor , 0, height / 2, gradiantEndColor, true);

	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, width, height);

	        Random r = new Random();

	        for (int i = 0; i < width - 10; i = i + 25) {
	            int q = Math.abs(r.nextInt()) % width;
	            int colorIndex = Math.abs(r.nextInt()) % 200;
	            g2d.setColor(new Color(colorIndex, colorIndex, colorIndex));
	            g2d.drawLine(i, q, width, height);
	            g2d.drawLine(q, i, i, height);
	        }

	        g2d.setColor(textColor);

	        int index = Math.abs(r.nextInt()) % newData.length;

	        String captcha = newData[index];
	        request.getSession().setAttribute("servlet_captcha", captcha);

	        int x = 0;
	        int y = 0;

	        for (int i = 0; i < newData[index].length(); i++) {
	            Font font = new Font(fontName , Font.BOLD, fontSize);
	            g2d.setFont(font);
	            x += xGap + (Math.abs(r.nextInt()) % 15);
	            y = yGap + Math.abs(r.nextInt()) % 20;

	            g2d.drawChars(newData[index].toCharArray(), i, 1, x, y);
	        }

	        for (int i = 0; i < width - 10; i = i + 25) {
	            int p = Math.abs(r.nextInt()) % width;
	            int q = Math.abs(r.nextInt()) % width;
	            int colorIndex = Math.abs(r.nextInt()) % 200;
	            g2d.setColor(new Color(colorIndex, colorIndex, colorIndex));
	            g2d.drawLine(p, 0, i + p, q);
	            g2d.drawLine(p, 0, i + 25, height);
	        }

	        g2d.dispose();

	        response.setContentType("image/png");
	        OutputStream os = response.getOutputStream();
	        ImageIO.write(bufferedImage, "png", os);
	        os.close();
	    }
/*
	protected void processRequest(HttpServletRequest request, 
	                                HttpServletResponse response) 
	                 throws ServletException, IOException {

	    int width = 150;
	    int height = 50;

	    char data[][] = {
	        { 'z', 'e', 't', 'c', 'o', 'd', 'e' },
	        { 'l', 'i', 'n', 'u', 'x' },
	        { 'f', 'r', 'e', 'e', 'b', 's', 'd' },
	        { 'u', 'b', 'u', 'n', 't', 'u' },
	        { 'j', 'e', 'e' }
	    };


	    BufferedImage bufferedImage = new BufferedImage(width, height, 
	                  BufferedImage.TYPE_INT_RGB);

	    Graphics2D g2d = bufferedImage.createGraphics();

	    Font font = new Font("Georgia", Font.BOLD, 18);
	    g2d.setFont(font);

	    RenderingHints rh = new RenderingHints(
	           RenderingHints.KEY_ANTIALIASING,
	           RenderingHints.VALUE_ANTIALIAS_ON);

	    rh.put(RenderingHints.KEY_RENDERING, 
	           RenderingHints.VALUE_RENDER_QUALITY);

	    g2d.setRenderingHints(rh);

	    GradientPaint gp = new GradientPaint(0, 0, 
	    Color.red, 0, height/2, Color.black, true);

	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, width, height);

	    g2d.setColor(new Color(255, 153, 0));

	    Random r = new Random();
	    int index = Math.abs(r.nextInt()) % 5;

	    String captcha = String.copyValueOf(data[index]);
	    request.getSession().setAttribute("captcha", captcha );

	    int x = 0; 
	    int y = 0;

	    for (int i=0; i<data[index].length; i++) {
	        x += 10 + (Math.abs(r.nextInt()) % 15);
	        y = 20 + Math.abs(r.nextInt()) % 20;
	        g2d.drawChars(data[index], i, 1, x, y);
	    }

	    g2d.dispose();

	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(bufferedImage, "png", os);
	    os.close();
	  } 


	public class CaptchaServlet extends HttpServlet {

	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        int width = 300;
	        int height = 60;
	        int fontSize = 26;
	        int xGap = 30 ;
	        int yGap = 25 ;
	        String fontName = "Arial" ;
	        Color gradiantStartColor = new Color(60, 60, 60); // dark grey
	        Color gradiantEndColor = new Color(140, 140, 140); // light grey
	        Color textColor =  new Color(255, 153, 0); // orange

	        String[] newData = {"hiworld", "orlando", "global", "publish", "looky"}; // you add more words or read them from db or something...

	        BufferedImage bufferedImage = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);

	        Graphics2D g2d = bufferedImage.createGraphics();

	        RenderingHints rh = new RenderingHints(
	                RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.setRenderingHints(rh);

	        GradientPaint gp = new GradientPaint(0, 0, gradiantStartColor , 0, height / 2, gradiantEndColor, true);

	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, width, height);

	        Random r = new Random();

	        for (int i = 0; i < width - 10; i = i + 25) {
	            int q = Math.abs(r.nextInt()) % width;
	            int colorIndex = Math.abs(r.nextInt()) % 200;
	            g2d.setColor(new Color(colorIndex, colorIndex, colorIndex));
	            g2d.drawLine(i, q, width, height);
	            g2d.drawLine(q, i, i, height);
	        }

	        g2d.setColor(textColor);

	        int index = Math.abs(r.nextInt()) % newData.length;

	        String captcha = newData[index];
	        request.getSession().setAttribute("captcha", captcha);

	        int x = 0;
	        int y = 0;

	        for (int i = 0; i < newData[index].length(); i++) {
	            Font font = new Font(fontName , Font.BOLD, fontSize);
	            g2d.setFont(font);
	            x += xGap + (Math.abs(r.nextInt()) % 15);
	            y = yGap + Math.abs(r.nextInt()) % 20;

	            g2d.drawChars(newData[index].toCharArray(), i, 1, x, y);
	        }

	        for (int i = 0; i < width - 10; i = i + 25) {
	            int p = Math.abs(r.nextInt()) % width;
	            int q = Math.abs(r.nextInt()) % width;
	            int colorIndex = Math.abs(r.nextInt()) % 200;
	            g2d.setColor(new Color(colorIndex, colorIndex, colorIndex));
	            g2d.drawLine(p, 0, i + p, q);
	            g2d.drawLine(p, 0, i + 25, height);
	        }

	        g2d.dispose();

	        response.setContentType("image/png");
	        OutputStream os = response.getOutputStream();
	        ImageIO.write(bufferedImage, "png", os);
	        os.close();
	    }
	    */
	  protected void doGet(HttpServletRequest request, 
	                       HttpServletResponse response)
	                           throws ServletException, IOException {
	      processRequest(request, response);
	  } 


	  protected void doPost(HttpServletRequest request, 
	                        HttpServletResponse response)
	                            throws ServletException, IOException {
	      processRequest(request, response);
	  }
	}