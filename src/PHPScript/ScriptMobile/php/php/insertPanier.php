
<?php
require_once('connect.php');

$produit=$_GET['produit'];
$etat=$_GET['etat'];
$user=$_GET['user'];



$sql = "INSERT INTO panier (produit,etat,user)
VALUES ( '$produit','En Cours','$user')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>