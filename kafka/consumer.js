const { Kafka } = require('kafkajs');
const msg = process.argv[2];

run();
async function run() {
  try {
    const kafka = new Kafka({
      clientId: 'myapp',
      brokers: ['localhost:9092']
    })

    const consumer = kafka.consumer({groupId: 'test'});
    console.log('Connection...');
    await consumer.connect();
    console.log('Connected!');

    await consumer.subscribe({
      topic: 'Users',
      fromBeginning: true
    })

    await consumer.run({
      eachMessage: async result => {
        console.log(`Recieved message ${result.message.value} on partition ${result.partition}`);
      }
    })
    // await consumer.disconnect();
  } catch (e) {
    console.log('Error: ', e);
  } finally {

  }
}