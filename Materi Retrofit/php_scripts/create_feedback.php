<?php
/*
 * Buat member baru
 */
// cek form
if (isset($_POST['subject'])&& isset($_POST['message'])&& isset($_POST['user']) && isset($_POST['email'])) {
    
		$subject 				= $_POST['subject'];
    $message 				= $_POST['message'];
		$user 					= $_POST['user'];
		$email 					= $_POST['email'];
   
	
    // include db connect
    require_once __DIR__ . '/db_connect.php';

    // konekin db
    $db = new DB_CONNECT();

    // insert ke db
						$result = mysql_query("INSERT INTO `komplain` 	(`id_db`,
																														 `id_member`, 
																														`phone_email`, 
																														`tgl_komplain`, 
																														`waktu_komplain`,
																														 `via`, 
																														`subject_komplain`,
																														 `isi_komplain`, 
																														`id_staff_respond`,
																														 `tgl_respond`, 
																														`waktu_respond`, 
																														`cat_respond`) 
																				         VALUES (		NULL, 
																														'$user',
																														'$user',
																														'',
																														'', 
																														'aplikasi', 
																														'$subject',
																														'$message', 
																														'',
																														 '',
																														 '', 
																														'')");

    // cek data udah masuk belum
    if ($result) {
        echo "sukses";
    } else {
        echo "gagal";
    }
} else {
     echo "gagal";
}
?>
