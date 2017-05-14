<?php
require_once('connect.php');

$nom=$_GET['nom'];
$pre=$_GET['pre'];
$username=$_GET['username'];
$email=$_GET['email'];
$pwd=$_GET['pwd'];
$phone=$_GET['phone'];
$role=$_GET['role'];


$sql = "INSERT INTO fos_user(nom, prenom, username, username_canonical, email, email_canonical, password,num_telephone,image,enable,roles)
VALUES ( '$nom','$pre','$username','$username','$email','$email','$pwd',$phone,'http://localhost/socialpromobile/uploads/$username.jpg',1,'$role')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>