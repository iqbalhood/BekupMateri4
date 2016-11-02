<?php

/*
 * kode untuk tampilak semua produk, pada halaman home
 */




// include db connect class
require_once __DIR__ . '/db_connect.php';

// ckonekin ke db
$db = new DB_CONNECT();
$hastag = "";


		//  get by hastag
		$result = mysql_query("SELECT * FROM DB ") or die(mysql_error());

		// cek
		if (mysql_num_rows($result) > 0) {
		    // looping hasil
		    // hastag node
		    
		    
		    while ($row = mysql_fetch_array($result)) {
			
			$hastag .= '{ id:"' . $row["id_db"].'", hastag:"'. $row["hastag"]. '"}'.',';
			
			
		    }
		    

		    // echo JSON response
		    echo "[".trim($hastag, ",")."]";
		   
		} else {
		    
			
		    echo  "Tidak ada data yang ditemukan";

		    
		}







?>
