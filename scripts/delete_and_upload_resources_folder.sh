#!/bin/bash

# Check to see whether the 'gdrive' program is installed
if ! [ -x "$(command -v gdrive)" ]; then
	echo "gdrive is not installed. Exiting";
	exit 1; 
fi

# Delete whatever is in the CnC folder on GDrive
echo -e "\n- Deleting contents of CnC folder on GDrive"
for id in `gdrive list -q "'1GMYycKdgEpFIgaZVopJqlHIKPH0w8pR3' in parents" | tail +2 | cut -d\  -f1` ; do gdrive delete -r $id > /dev/null; done
echo -e "- Finished deleting contents of CnC folder on GDrive\n"

# Compress the resources folder before uploading
echo -e "- Compressing Resources Folder"
tar -czf resources.tar.gz ../resources/src/main/resources/ &> /dev/null
echo -e "- Finished Compressing Resources Folder\n"

# Upload the tarball to the CnC folder on GDrive
echo -e "- Uploading resources.tar.gz tarball to GDrive"
gdrive upload -r -p '1GMYycKdgEpFIgaZVopJqlHIKPH0w8pR3' resources.tar.gz > /dev/null
echo -e "- Finished uploading resources.tar.gz tarball to GDrive\n"

# Delete the resources tarball once uploaded
echo -e "- Removing resources.tar.gz"
rm resources.tar.gz
echo -e "- Finished removing resources.tar.gz\n"
