
<?php
require_once('connect.php');



$sql = "Select numero from panier where etat='Fini' ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
		while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('panier');
		 $mydata->addChild('numero',$row['numero']);
	
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>