
<?php
require_once('connect.php');
$user=$_GET['user'];


$sql = "update panier SET etat='Fini' WHERE etat='En Cours' AND user='$user'";


if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error";
}

mysqli_close($conn);
?>