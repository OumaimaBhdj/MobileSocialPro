<?php

$nom = $_POST['nom'];


$path = "uploadsEvents/$nom.jpg";

try {
        $file1 = $_FILES['file'];

        if (is_uploaded_file($file1['tmp_name'])) {
            
            if (move_uploaded_file($file1['tmp_name'], $path)) {
                echo ("success");
            }else{
                echo ("0");
            }
        }
    } catch(Exception $ex){
        echo ("ERROR");
        exit(1);
    }



?>