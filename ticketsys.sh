chdir Inputs/Login
for i in *
do
    echo "Running Login test: $i"
    ../sys < $i > ../../Outputs/Login/$i.out
done

