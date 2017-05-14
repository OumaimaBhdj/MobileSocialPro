<?php
require_once('connect.php');
$id=$_GET['id'];
$datedebut=$_GET['datedebut'];
$nbrjours=$_GET['nbrjours'];
$type_conge=$_GET['type_conge'];
$raison=$_GET['raison'];
$sql = "UPDATE `conge` SET `datedebut`='$datedebut',`nbrjours`='$nbrjours',`type_conge`='$type_conge',`raison`='$raison'  where id=$id";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>