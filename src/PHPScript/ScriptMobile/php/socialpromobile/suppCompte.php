<?php
require_once('connect.php');
$id=$_GET['id'];

$sql = "DELETE FROM `fos_user` WHERE id =$id";

if (mysqli_query($conn, $sql)) {
    	 echo "success";
} else {
    echo "Error";
}

mysqli_close($conn);
?>