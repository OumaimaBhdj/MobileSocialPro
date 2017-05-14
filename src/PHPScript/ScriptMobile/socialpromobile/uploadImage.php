<?php
//$image = $_POST['image'];
//php side
$username = $_POST['username'];


$path = "uploads/$username.jpg";

try {
        $file1 = $_FILES['file'];

        if (is_uploaded_file($file1['tmp_name'])) {
            
            if (move_uploaded_file($file1['tmp_name'], $path)) {
                
                echo ("success");
            }else{
                echo json_encode("0");
            }
        }
    } catch(Exception $ex){
        echo json_encode("ERROR");
        exit(1);
    }



?>