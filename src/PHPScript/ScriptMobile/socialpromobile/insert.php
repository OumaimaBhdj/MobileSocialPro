<?php
require_once('connect.php');

$nom=$_GET['nom'];
$categorie=$_GET['categorie'];
$nbrelimite=$_GET['nbrelimite'];
$description=$_GET['description'];
$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];
$sql = "INSERT INTO evenement ( nom, categorie, nbrelimite, description, datedebut, datefin)
VALUES ( '$nom','$categorie','$nbrelimite','$description','$datedebut','$datefin')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>