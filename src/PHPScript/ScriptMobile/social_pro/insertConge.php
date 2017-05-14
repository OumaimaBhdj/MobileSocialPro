<?php
require_once('connect.php');
$datedebut=$_GET['datedebut'];
$nbrjours=$_GET['nbrjours'];
$type_conge=$_GET['type_conge'];
$raison=$_GET['raison'];
$id = $_GET['id'];
$sql = "INSERT INTO conge ( datedebut, nbrjours, type_conge,raison,etat,user)
VALUES ( '$datedebut','$nbrjours','$type_conge','$raison','En attente',$id)";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>