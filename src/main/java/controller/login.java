package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginBean;
import model.UsersLists;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//bean usato per accedere ai dati dell`autenticazione
    private LoginBean login;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// istanzio il bean di login
		login = new LoginBean();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prendo gli input del form di login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//utilizzo il bean login per controllare l`esistenza dell`utente nel database
		if(login.checkUser(username, password))
		{
			//utente esiste, quindi creo la sessione e lo porto alla pagina principale
			HttpSession session = request.getSession(false);
			if(session!=null)
			{
				//invalido eventualmente una sessione già esistente
				session.invalidate();
			}
			//creo la sessione
			session = request.getSession();
			session.setAttribute("username", username);
			
			//creo l`array con tutte le liste dell`utente
			UsersLists usersLists = login.fetchUsersLists(username);
			//aggiungo l`array di liste alla session in modo da poter essere utilizzato dalla pagina index per comporre la grid di liste
			session.setAttribute("usersLists", usersLists);
			System.out.println(usersLists.toString());
			
			//forward della richiesta alla pagina jsp principale
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
		{
			//lancio un eccezione in caso di errore che verrà gestita dalla pagina ErrorPage.jsp
			throw new ServletException("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
