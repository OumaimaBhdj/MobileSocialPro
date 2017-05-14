<?php
// tache non afféctées
require_once('connect.php');
$sql = "select * FROM `Tache` where datefin> CURRENT_DATE and user_id is NULL  ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>'); 
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('tache');
        $mydata->addChild('id',$row['id']);
		$mydata->addChild('datedebut',$row['datedebut']);
		$mydata->addChild('datefin',$row['datefin']);
		$mydata->addChild('description',utf8_decode($row['description']));
        
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>