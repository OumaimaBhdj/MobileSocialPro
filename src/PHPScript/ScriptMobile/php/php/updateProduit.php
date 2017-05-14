<?php
require_once('connect.php');
$id=$_GET['id'];
$libelle=$_GET['libelle'];
$typeproduit=$_GET['typeproduit'];
$prix=$_GET['prix'];
$quantite=$_GET['quantite'];


$imagename=$_GET['imagename'];
$imagepath=$_GET['imagepath'];


$file="C:/wamp/www/imageMobile/".$imagename;
file_put_contents($file,$imagepath);

copy($imagepath,"C:/wamp/www/imageMobile/".$imagename);




$sql = "UPDATE `produit` SET `libelle`='$libelle',`typeproduit`='$typeproduit',`prix`='$prix',`quantite`='$quantite',`path`='$file'   where id=$id";


if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error";
}

mysqli_close($conn);
?>