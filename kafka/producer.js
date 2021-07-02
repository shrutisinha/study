const { Kafka } = require('kafkajs');
const msg = process.argv[2];

run();
async function run() {
  try {
    const kafka = new Kafka({
      clientId: 'myapp',
      brokers: ['localhost:9092']
    })

    const producer = kafka.producer();
    console.log('Connection...');
    await producer.connect();
    console.log('Connected!');
    //A-M: partition 0, N-Z: partition 1
    const partition = msg[0] > 'N' ? 0 : 1;
    const res = await producer.send({
      topic: 'Users',
      messages: [
        {
          value: msg,
          partition
        }
      ]
    })

    console.log('Send successfully!');
    console.log(res);
    await producer.disconnect();
  } catch (e) {
    console.log('Error: ', e);
  } finally {
    process.exit();
  }
}