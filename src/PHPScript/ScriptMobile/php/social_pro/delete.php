<?php
require_once('connect.php');

$id=$_GET['id'];
$sql = " delete From Tache where id=$id";
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>