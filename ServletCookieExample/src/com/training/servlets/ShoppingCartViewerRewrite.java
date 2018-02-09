package com.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoppingCartViewerRewrite
 */
@WebServlet("/ShoppingCartViewerRewrite")
public class ShoppingCartViewerRewrite extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
	IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<HEAD><TITLE>Current Shopping Cart Items</TITLE></HEAD>");
		out.println("<BODY>");

		// Get the current session ID, or generate one if necessary
		String sessionid = req.getPathInfo();
		if (sessionid == null) {
			sessionid = generateSessionId();
		}

		// Cart items are associated with the session ID
		String[] items = getItemsFromCart(sessionid);

		// Print the current cart items.
		out.println("You currently have the following items in your cart:<BR>");
		if (items == null) {
			out.println("<B>None</B>");
		} else {
			out.println("<UL>");
			for (int i = 0; i < items.length; i++) {
				out.println("<LI>" + items[i]);
			}
			out.println("</UL>");
		}

		// Ask if the user wants to add more items or check out.
		// Include the session ID in the action URL.
		out.println("<FORM ACTION=\"/servlet/ShoppingCart/" + sessionid + "\" METHOD=POST>");
		out.println("Would you like to<BR>");
		out.println("<INPUT TYPE=SUBMIT VALUE=\" Add More Items \">");
		out.println("<INPUT TYPE=SUBMIT VALUE=\" Check Out \">");
		out.println("</FORM>");

		// Offer a help page. Include the session ID in the URL.
		out.println("For help, click <A HREF=\"/servlet/Help/" + sessionid
				+ "?topic=ShoppingCartViewerRewrite\">here</A>");

		out.println("</BODY></HTML>");
	}

	private static String generateSessionId() throws UnsupportedEncodingException {
		String uid = new java.rmi.server.UID().toString(); // guaranteed unique
		return URLEncoder.encode(uid, "UTF-8"); // encode any special chars
	}

	private static String[] getItemsFromCart(String sessionid) {
		return new String[] { "a", "b" };
	}
}
