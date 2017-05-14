<?php
require_once('connect.php');
$id=$_GET['id'];
$nom=$_GET['nom'];
$prenom=$_GET['prenom'];
$username=$_GET['username'];
$email=$_GET['email'];
$password=$_GET['password'];
$phone=$_GET['phone'];

$sql = "UPDATE `fos_user` SET `username`='$username',`username_canonical`='$username',`email`='$email',`email_canonical`='$email',`password`='$password',`num_telephone`='$phone' ,`prenom`='$prenom',`nom`='$nom' where id =$id";

if (mysqli_query($conn, $sql)) {
    	$sql = "SELECT * FROM `fos_user` WHERE `username`='$username' and `password`='$password'";
	$res = mysqli_query($conn,$sql);
	$emarray = Array();

if ($res->num_rows > 0) {
    // output data of each row
	echo('{"user":[');
    while($row=mysqli_fetch_assoc($res)) {
      	$emarray = $row;
	echo json_encode($emarray);
       }
} else {
    echo "Error";
}
}
mysqli_close($conn);
?>