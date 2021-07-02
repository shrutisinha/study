const { Kafka } = require('kafkajs');
run();
async function run() {
  try {
    const kafka = new Kafka({
      clientId: 'myapp',
      brokers: ['localhost:9092']
    })

    const admin = kafka.admin();
    console.log('Connection...');
    await admin.connect();
    console.log('Connected!');

    //A-M: partition 0, N-Z: partition 1
    await admin.createTopics({
      topics: [{
        topic: 'Users',
        numPartitions: 2
      }]
    })
    console.log('Created successfully!');
    await admin.disconnect();
  } catch (e) {
    console.log('Error: ', e);
  } finally {
    process.exit();
  }
}