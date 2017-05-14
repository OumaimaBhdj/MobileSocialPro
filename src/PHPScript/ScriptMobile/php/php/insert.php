<?php
require_once('connect.php');

$typeproduit=$_GET['typeproduit'];
$libelle=$_GET['libelle'];
$prix=$_GET['prix'];
$quantite=$_GET['quantite'];


$imagename=$_GET['imagename'];
$imagepath=$_GET['imagepath'];


$file="C:/wamp/www/imageMobile/".$imagename;
file_put_contents($file,$imagepath);

copy($imagepath,"C:/wamp/www/imageMobile/".$imagename);

$sql = "INSERT INTO produit (typeproduit,libelle,prix,quantite,path)
VALUES ( '$typeproduit','$libelle','$prix','$quantite','$file')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>