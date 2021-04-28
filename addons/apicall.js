const { MessageEmbed } = require('discord.js');
const axios = require('axios').default;
module.exports = {
    name: "api",
    category: "extra",
    run: async (client, message, args) => {
        if (!args[0]) {
            return message.channel.send("Uso: `a!api <id> `")
        } 
        axios
        .get('http://localhost:8080/products')
        .then((response) => {
            console.log('RESPONSE: ', response)
            const embed = new MessageEmbed()
            .setColor('#EFFF00')
            .setTitle('API-SPRING')
            .addFields(
                { name: 'ID', value: (response.data[args[0]].id) },
                { name: 'Nombre', value: (response.data[args[0]].name) }
            );
            message.channel.send(embed);

        })

    }
}
