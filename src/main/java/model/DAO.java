package model;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
	
	private Connection conn;
	SMTP mail;

	public DAO() {
		//connessione al database col driver JDBC
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/TPSITToDoList", "admin", "admin");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mail = new SMTP();
	}
	
	public boolean addUser(Utente user) {
		
		Boolean check = false;
		
		if(this.checkDBCollision(user)){
		
			Data data = new Data();
			String password = this.encryptPass(user.getPassword());
		
			String query = "INSERT INTO `utente`(`username`, `email`, `password`, `data_iscrizione`) VALUES ('"+user.getUsername()+"','"+user.getEmail()+"','"+password+"','"+data.getData()+"')";
			
			
			try {
				int res = conn.createStatement().executeUpdate(query);
				if(res!=0)
				{
					check = true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
    }
	
	public boolean checkUser(Utente user){
		
		String password = this.encryptPass(user.getPassword());
		String query = "SELECT username FROM utente WHERE (username='"+user.getUsername()+"' OR email='"+user.getUsername()+"') AND password='"+password+"'";
		
		Boolean check = false;
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public String encryptPass(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
		//retrieve bytes to encrypt
		            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
		            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
		//perform encryption
		            for (int i = 0; i < encodedhash.length; i++) {
		                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
		                if (hexVal.length() == 1) {
		                    encryptionValue.append('0');
		                }
		                encryptionValue.append(hexVal);
		            }
		//return encrypted value
		            return encryptionValue.toString();
		} catch (Exception ex) {
		            return ex.getMessage();
		  }
	}
	
	public boolean checkPassword(String password, String hash){
		
		//verifico se la password inserita coincide con l`hash della password
		return this.encryptPass(password).equals(hash);
	}
	
	public boolean checkDBCollision(Utente user) {
		
		String query = "SELECT username FROM utente WHERE (username='"+user.getUsername()+"' OR email='"+user.getEmail()+"')";
		
		Boolean check = true;
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				check = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
		
	}
	
	public int findUserID(String username) {
		
		String query = "SELECT id_utente FROM utente WHERE username='"+username+"'";
		
		int id = -1;
		
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				id = res.getInt("id_utente");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public ArrayList<Todo> fetchTodos(int id){
		
		ArrayList<Todo> todos = new ArrayList<Todo>();
		
		String query = "SELECT `id_todo`,`titolo_todo`,`descrizione_todo`,`isDone` FROM `todo` WHERE `id_lista` = '"+id+"'";
		
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			while(res.next())
			{
				//prendo i parametri datomi dalla query al DB
				int id_todo = res.getInt("id_todo");
				String titolo = res.getString("titolo_todo");
				String descrizione = res.getString("descrizione_todo");
				Boolean isDone = res.getBoolean("isDone");
				//creo un nuovo oggetto todo
				Todo todo = new Todo(id_todo,titolo,descrizione,isDone);
				//lo aggiungo all`arraylist
				todos.add(todo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return todos;
	}
	
	public UsersLists fetchUsersLists(String username){
		
		UsersLists usersLists = new UsersLists();
		 
		int id = this.findUserID(username);
		String query = "SELECT `id_lista`,`titolo_lista` FROM `lista` WHERE `id_utente`='"+id+"'";
		
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			while(res.next())
			{
				//prendo i parametri datomi dalla query al DB
				int id_lista = res.getInt("id_lista");
				String titolo = res.getString("titolo_lista");
				//richiamo la funzione per ricercare tutte le todos appartenenti a quella lista
				ArrayList<Todo> todos = this.fetchTodos(id_lista);
				//creo un nuovo oggetto lista
				Lista lista = new Lista(id_lista,titolo,todos);
				//lo aggiungo alla usersLists
				usersLists.addUsersLists(lista);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usersLists;
	}
	
public boolean checkEmail(Utente user){
		
		String query = "SELECT username FROM utente WHERE email='"+user.getEmail()+"'";
		
		Boolean check = false;
		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public boolean updatePassword(Utente user) {
		
		Boolean check = false;
		String password = this.encryptPass(user.getPassword());
		String query = "UPDATE `utente` SET `password`='"+password+"' WHERE `email` = '"+user.getEmail()+"'";
		
		try {
			int res = conn.createStatement().executeUpdate(query);
			if(res!=0)
			{
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean addTodo(int id_lista) {
		
		Boolean check = false;
		String query = "INSERT INTO `todo`(`id_lista`) VALUES ('"+id_lista+"')";
		
		try {
			int res = conn.createStatement().executeUpdate(query);
			if(res!=0)
			{
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	
	public int addLista(Lista lista, Utente user) {
		
		int id = -1;
		int id_utente = this.findUserID(user.getUsername());
		String titolo = lista.getTitolo();
		Data data = new Data();
		
		String query = "INSERT INTO `lista`(`id_utente`, `titolo_lista`, `data_diCreazione`) VALUES ('"+id_utente+"','"+titolo+"','"+data.getData()+"')";
		
		try {
			int res = conn.createStatement().executeUpdate(query);
			if(res!=0)
			{
				query = "SELECT `id_lista` FROM `lista` WHERE `id_utente`='"+id_utente+"' AND `titolo_lista` = '"+titolo+"' AND `data_diCreazione` = '"+data.getData()+"'";
				try {
					ResultSet ress = conn.createStatement().executeQuery(query);
					if(ress.next())
					{
						id = ress.getInt("id_lista");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public boolean deleteLista(int id_lista,Utente user) {
		
		Boolean check = false;
		//query per ricavare tutti gli id delle todo collegate alla lista
		String query = "SELECT `id_todo` FROM `todo` WHERE `id_lista` = '"+id_lista+"'";
		
		int id_utente = this.findUserID(user.getUsername());
		try {
			ResultSet ress = conn.createStatement().executeQuery(query);
			while(ress.next())
			{
				//per ogni todo eseguo una query per eleminarla
				int id_todo = ress.getInt("id_todo");
				this.deleteTodo(id_todo,null,false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dopo aver eliminato le todo procedo con la cancellazione della lista
		query = "DELETE FROM `lista` WHERE `id_lista`='"+id_lista+"' AND `id_utente` = '"+id_utente+"'";

		try {
			int res = conn.createStatement().executeUpdate(query);
			if(res!=0) {
				check = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return check;
	}
	
	public boolean deleteTodo(int id_todo,Utente user, Boolean isExternal) {
		
		Boolean check = false;
		String query;
		
		if(isExternal) {
			int id_utente = this.findUserID(user.getUsername());
			//query più complessa per verificare l`autenticità della richiesta (solo chi è proprietario della todo può eliminarla)
			query = "DELETE `todo` FROM `todo` INNER JOIN lista ON lista.id_lista=todo.id_lista WHERE `id_todo` = '"+id_todo+"' AND lista.id_utente = '"+id_utente+"';";
		}else {
			query = "DELETE FROM `todo` WHERE `id_todo` = '"+id_todo+"'";
		}

		try {
			int res = conn.createStatement().executeUpdate(query);
			if(res!=0) {
				check = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	public boolean modifyTodo(int id_lista,Utente user,Todo todo) {
		
		Boolean check = false;
		
		int id_utente = this.findUserID(user.getUsername());
		int id_todo = todo.getId();
		
		//prima query di controllo per verifica la proprietà della todo
		String query = "SELECT `id_todo` FROM `todo` INNER JOIN lista ON lista.id_lista=todo.id_lista INNER JOIN utente ON lista.id_utente=utente.id_utente WHERE `id_todo` = '"+id_todo+"' AND lista.id_lista = '"+id_lista+"' AND utente.id_utente = '"+id_utente+"'";

		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				//se esiste almeno un risultato allora procedo con l`eliminazione
				query = "UPDATE `todo` SET `titolo_todo`='"+todo.getTitolo()+"',`descrizione_todo`='"+todo.getDescrizione()+"' WHERE `id_todo` = '"+id_todo+"'";
				int ress = conn.createStatement().executeUpdate(query);
				if(ress!=0) {
					check = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
public boolean checkTodo(int id_lista,Utente user,Todo todo) {
		
		Boolean check = false;
		
		int id_utente = this.findUserID(user.getUsername());
		int id_todo = todo.getId();
		
		//prima query di controllo per verifica la proprietà della todo
		String query = "SELECT `isDone` FROM `todo` INNER JOIN lista ON lista.id_lista=todo.id_lista INNER JOIN utente ON lista.id_utente=utente.id_utente WHERE `id_todo` = '"+id_todo+"' AND lista.id_lista = '"+id_lista+"' AND utente.id_utente = '"+id_utente+"'";

		try {
			ResultSet res = conn.createStatement().executeQuery(query);
			if(res.next())
			{
				//se esiste almeno un risultato allora procedo con l`update invertendo il valore presente nel database
				int isDone = 1;
				if(res.getBoolean("isDone")) {
					isDone = 0;
				}
				
				query = "UPDATE `todo` SET `isDone`='"+isDone+"' WHERE `id_todo` = '"+id_todo+"'";
				int ress = conn.createStatement().executeUpdate(query);
				if(ress!=0) {
					check = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
}