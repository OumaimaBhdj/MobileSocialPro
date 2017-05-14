<?php
require_once('connect.php');

$date=date("Y-m-d");
$text=$_GET['text'];
$objet=$_GET['objet'];
$user_id=$_GET['user_id'];
$sql = "INSERT INTO message ( objet, text, user_id,datedenvoye)
VALUES ( '$objet','$text','$user_id','$date')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>