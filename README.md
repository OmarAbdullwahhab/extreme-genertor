# extreme-genertor
A very simple, yet very powerfull any code,text,...etc generator based of free maker template engine.
it uses the apache freemarker template engine and the yaml.
freemarker is used to define the template to be generated, yaml is used to define the model for that template.

# Prerequisites 
    - knowledge of freemaker template engine https://freemarker.apache.org/ and how it works.
    - knowledge of YAML https://yaml.org/
    
# Usage
    - compile the code with maven
    - run the code to see the usage message.
    - copy the resource/example directory somewhere
    - run again and pass the arguments as follows.
        java -jar ExtremeGenerator-1.0.jar <somewhere> form.ftl <somewhere>/model.yaml

