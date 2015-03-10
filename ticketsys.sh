# Run the tests, check the outputs
chdir Inputs
for f in *
do
    echo $ftilda
    chdir $f
    for i in *
    do
        echo "Running test $f-$i"
        chdir ../..
        ./sys < Inputs/$f/$i > Outputs/$f/${i%.*}.out
        chdir Inputs/$f
    done
    chdir ../..
    chdir Inputs
done

# check transaction file differences
chdir ..
rm all.etf
chdir Expected
for f in *
do
    chdir $f
    for i in *.etf
    do
        echo "At $f-$i"
        chdir ../..
        cat Expected/$f/$i >> all.etf
        chdir Expected/$f
    done
    chdir ../..
    chdir Expected
done

chdir ..

diff -c all.etf daily.txt > test.txt

rm diff.txt
# check terminal output differences
chdir Outputs
for f in *
do
    chdir $f
    for i in *
    do
        echo "At $f-$i"
        chdir ../..
        diff -c Expected/$f/${i%.*}.bto Outputs/$f/${i%.*}.out >> diff.txt
        chdir Outputs/$f
    done
    chdir ../..
    chdir Outputs
done

