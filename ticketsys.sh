chdir Inputs
for f in *
do
    echo $f
    chdir $f
    for i in *
    do
        echo "Running test $f-$i"
        chdir ../..
        ./sys < Inputs/$f/$i > Outputs/$f/$i.out
        chdir Inputs/$f
    done
    chdir ../..
    chdir Inputs
done

