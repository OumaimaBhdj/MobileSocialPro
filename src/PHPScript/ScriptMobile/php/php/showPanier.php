


<?php
require_once('connect.php');
$user=$_GET['user'];


$sql = "SELECT prix,libelle FROM produit,panier where produit.id=panier.produit and panier.etat='En Cours' and user='$user' ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
		while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('panier');
		 $mydata->addChild('prix',$row['prix']);
		 $mydata->addChild('libelle',$row['libelle']);
     
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>