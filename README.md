# Neural Network

This repo contains code for a working Neuralt Network implementation in Java. The network can be constructed as 
follows:

```Java
int[] layers = {5, 7, 8, 2};
Network N = new Network(layers);
```

where `layers` is an array specifying the number of hidden nodes in each layer of the network. Further parameters 
can be added to the constructor to specify stuff like activations for each layer, cost function, and learning rate.
The `Network` can the be trained with:

```Java
N.train(input, expected);
```

where `input` is the input Matrix and `expected` is the expected output of the network. Example usage of the Network
on the [MNIST](http://yann.lecun.com/exdb/mnist/) dataset can be found the `Driver.java` file (the actual data is 
not included within the repo and must be downloaded separately and placed within a `data` directory). Supported
activations and cost functions can be found within their respective folders.

