
<?php
require_once('connect.php');
$id=$_GET['id'];
$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];
$description=$_GET['description'];

$sql = "UPDATE `tache` SET `datedebut`='$datedebut',`datefin`='$datefin',`description`='$description' where id=$id";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>