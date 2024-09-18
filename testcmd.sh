files=("file1.txt" "file2.txt")
for f in "${!files[@]}"
do
      echo "$f ${array[f]}"
done	
		
