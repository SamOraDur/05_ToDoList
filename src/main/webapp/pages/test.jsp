<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-full bg-white dark:bg-black">
<head>
<meta charset="UTF-8">
<title> Noirell </title>
<link rel="icon" href="img/Logo.png" type="image/png" />
<link rel="stylesheet" href="style/index.css">
</head>
<body class="h-full">

<div class="antialiased bg-gray-50 dark:bg-gray-900">
    <nav class="bg-white border-b border-gray-200 px-4 py-1.5 dark:bg-gray-800 dark:border-gray-700 fixed left-0 right-0 top-0 z-50">
      <div class="flex flex-wrap justify-between items-center">
        <div class="flex justify-start items-center">
              <!-- INIZIO NAV BAR CON LOGO E NOME APPLICAAZIONE -->
          <a href="../index.jsp" class="flex items-center justify-between mr-4">
            <img
              src="img/Logo.png"
              class="mr-3 h-8"
              style="width: 4rem; height: 4rem;"
            />
          </a>
        </div>
          <button
            type="button"
            class="flex mx-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
            id="user-menu-button"
            aria-expanded="false"
            data-dropdown-toggle="dropdown"
          >
          	<a href="account.jsp" class="flex mr-4">
              <!-- PROFILO UTENTE ( ICONA IN ALTO A DX ) -->
            <img
              class="w-8 h-8 rounded-full"
              src="img/noUserImage.png"
              alt="user photo"
            />
            </a>
          </button>
            <!-- INIZIO DROPDOWN  -->

        </div>
      </div>
    </nav>

    <!-- INIZIO SIDEBAR -->

    <aside
      class="fixed top-0 left-0 z-40 w-64 h-screen pt-14 transition-transform -translate-x-full bg-white border-r border-gray-200 md:translate-x-0 dark:bg-gray-800 dark:border-gray-700"
      aria-label="Sidenav"
      id="drawer-navigation"
    >
      <div class="overflow-y-auto py-5 px-3 h-full bg-white dark:bg-gray-800">
        <ul class="space-y-2">
        <!-- INIZIO SIDEBAR LISTA -->
          <li>     
            <a
              href="../index.jsp"
              class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
            >
             <!-- IMMAGINE LISTA -->
              <svg  
                aria-hidden="true"
                class="flex-shrink-0 w-6 h-6 text-gray-500 transition duration-75 group-hover:text-gray-900 dark:text-gray-400 dark:group-hover:text-white"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                 d="M7 3a1 1 0 000 2h6a1 1 0 100-2H7zM4 7a1 1 0 011-1h10a1 1 0 110 2H5a1 1 0 01-1-1zM2 11a2 2 0 012-2h12a2 2 0 012 2v4a2 2 0 01-2 2H4a2 2 0 01-2-2v-4z"
                  clip-rule="evenodd"
                ></path> 
                 <!-- FINE IMMAGINE LISTA -->
              </svg>
              <span class="ml-3">Liste</span>
            </a>
          </li> 
          <!-- FINE SIDEBAR LISTA -->
          <!-- INIZIO SIDEBAR ACCOUNT -->
          <li>     
            <a
              href="account.jsp"
              class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
            >
            
             <!-- IMMAGINE ACCOUNT -->
              <svg  
                aria-hidden="true"
                class="flex-shrink-0 w-6 h-6 text-gray-500 transition duration-75 group-hover:text-gray-900 dark:text-gray-400 dark:group-hover:text-white"
                fill="currentColor"
                viewBox="0 0 16 16"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                 d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"
                  clip-rule="evenodd"
                ></path> 
                 <!-- FINE IMMAGINE ACCOUNT -->
              </svg>
              <span class="ml-3">Account</span>
            </a>
          </li> 
          <!-- FINE SIDEBAR ACCOUNT -->
          
          <!-- INIZIO SIDEBAR LOGOUT -->
          <li>     
            <a
              href="logout.jsp"
              class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
            >
            
             <!-- IMMAGINE LOGOUT -->
              <svg  
                aria-hidden="true"
                class="flex-shrink-0 w-6 h-6 text-gray-500 transition duration-75 group-hover:text-gray-900 dark:text-gray-400 dark:group-hover:text-white"
                fill="currentColor"
                viewBox="0 0 16 16"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                 d="M12.5 15a.5.5 0 0 1-.5-.5v-13a.5.5 0 0 1 1 0v13a.5.5 0 0 1-.5.5zM10 8a.5.5 0 0 1-.5.5H3.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L3.707 7.5H9.5a.5.5 0 0 1 .5.5z"
                  clip-rule="evenodd"
                ></path> 
                 <!-- FINE IMMAGINE LOGOUT -->
              </svg>
              <span class="ml-3">Logout</span>
            </a>
          </li> 
          <!-- FINE SIDEBAR LOGOUT -->     
        
        </div>
      </div>
    </aside>

<!--INIZIO PARTE LISTE  -->
    <main class="p-4 md:ml-64 h-auto pt-20 dark:bg-gray-900">
		
		<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
  <table class="w-full text-left text-sm text-gray-500 rtl:text-right dark:text-gray-400">
    <thead class="bg-gray-50 text-xs uppercase text-gray-700 dark:bg-gray-700 dark:text-gray-400">
      <tr>
        <th scope="col" class="p-4">
            <div class="flex items-center">
                <input id="checkbox-all-search" type="checkbox" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                <label for="checkbox-all-search" class="sr-only">checkbox</label>
            </div>
        </th>
        <th scope="col" class="px-6 py-3">Titolo</th>
        <th scope="col" class="px-6 py-3">Descrizione</th>
        <th scope="col" class="px-3 py-3">
          <span class="sr-only">Modifica</span>
        </th>
        <th scope="col" class="px-3 py-3">
          <span class="sr-only">Rimuovi</span>
        </th>
      </tr>
    </thead>
    <tbody>
      <tr class="border-b bg-white hover:bg-gray-50 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-600">
        <td class="w-4 p-4">
            <div class="flex items-center">
                <input id="checkbox-table-search-1" type="checkbox" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                <label for="checkbox-table-search-1" class="sr-only">checkbox</label>
            </div>
        </td>
        <th scope="row" class=" px-6 py-4 font-medium text-gray-900 dark:text-white">Apple MacBook Pro 17"</th>
        <td class="px-6 py-4 whitespace-nowrap">Lapt</td>
        <td class="px-1 py-4 text-right">
          <a href="#" class="font-medium text-blue-600 hover:underline dark:text-blue-500">Modifica</a>
        </td>
        <td class="px-1 py-4 text-center">
          <a href="#" class="font-medium text-red-600 hover:underline dark:text-red-500">Rimuovi</a>
        </td>
      </tr>
    </tbody>
  </table>
</div>
		

   
    </main>
  </div>
  
  

</body>
</html>