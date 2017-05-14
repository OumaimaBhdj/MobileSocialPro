<?php
require_once('connect.php');
$id=$_GET['id'];

$sql = "DELETE FROM `evenement` WHERE id =$id";

if (mysqli_query($conn, $sql)) {
    	 echo "success";
} else {
    echo mysqli_error($conn);
}

mysqli_close($conn);
?>