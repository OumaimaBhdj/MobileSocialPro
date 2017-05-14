<?php
require_once('connect.php');

$nom=$_GET['nom'];
$user_id=$_GET['user_id'];
$categorie=$_GET['categorie'];
$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];
$description=$_GET['description'];
$nbrelimite=$_GET['nbrelimite'];
$lieu=$_GET['lieu'];


$sql = "INSERT INTO evenement(nom, categorie, datedebut, datefin, description, nbrelimite, user_id,nbrparticipants,flag,lieu)
VALUES ('$nom','$categorie','$datedebut','$datefin','$description',$nbrelimite,$user_id,0,1,'$lieu')";
$lyoum = date("Y/m/d");
if ($datedebut>$datefin && $datedebut>=$lyoum){
	echo "DATE ERROR";
	
}else{
	if (mysqli_query($conn, $sql)) {
	$sql2 = "SELECT * from evenement order by id desc limit 1";
	$res = mysqli_query($conn, $sql2);
	$row=mysqli_fetch_assoc($res);

	$id =  $row['id'];

	$sql2 = "UPDATE evenement set image = 'http://localhost/socialpromobile/uploadsevents/$id.jpg' where id=$id";
	
	if (mysqli_query($conn, $sql2)) {
		echo $id;
	}else{
		echo "error";
	}

	   	
} else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);

}
}



mysqli_close($conn);
?>