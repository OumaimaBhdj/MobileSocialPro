<?php
require_once('connect.php');

$id=$_GET['id'];
$nom=$_GET['nom'];
$categorie=$_GET['categorie'];
$nbrelimite=$_GET['nbrelimite'];
$description=$_GET['description'];
$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];

$sql = "UPDATE evenement  set nom='$nom', categorie='$categorie', nbrelimite='$nbrelimite', description='$description', datedebut='$datedebut', datefin='$datefin' where id=$id";


if (mysqli_query($conn, $sql)) {
  	$emarray = array();
		$sql = "SELECT * FROM evenement where id=$id";
		$result = mysqli_query($conn,$sql);

		if ($result->num_rows > 0) {
		    // output data of each row
			echo('{"events":[');
		    while($row=mysqli_fetch_assoc($result)) {
		      	$emarray = $row;
			echo json_encode($emarray);
		       }
			echo(']}');
			
		} else {
		    echo "0 results";
		}
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>