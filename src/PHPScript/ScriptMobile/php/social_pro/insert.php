<?php
require_once('connect.php');

$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];
$description=$_GET['description'];
$etat=$_GET['etat'];
$sql = "INSERT INTO tache ( datedebut, datefin, description,etat)
VALUES ( '$datedebut','$datefin','$description','$etat')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>