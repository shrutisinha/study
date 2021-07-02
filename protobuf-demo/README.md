JSON is not structured data
file size of JSON can go very large

Protocol buffers has structured data 
forces to use structure and schema

protoc-> feed protofile and what lang needed -> we get compiled file for that lang
lang independent (not agnoistic as work has to be done)

serialize to binary serializeBinary() (much smaller size)
deserialize from binary deserializeBinary()


????Check: TCP rount trips???


Pros
1. schema (optimizations easy, less error prone)
2. compact/small footprint
3. language neutral (like a lang in itself)

Cons
1. time taking, have to keep running protoc and regenrate the code
2. forcing schema (reason people moved from soap to rest was lack of schema), use rest if schema not needed (small application)