<?php
require_once('connect.php');


$typeproduit=$_GET['typeproduit'];
$sql = "SELECT * FROM `produit` where typeproduit='$typeproduit' ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
		while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('produit');
		 $mydata->addChild('id',$row['id']);
		 $mydata->addChild('description',$row['description']);
        $mydata->addChild('libelle',$row['libelle']);
		$mydata->addChild('typeproduit',$row['typeproduit']);
		$mydata->addChild('prix',$row['prix']);
$mydata->addChild('quantite',$row['quantite']);
$mydata->addChild('path',$row['path']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>