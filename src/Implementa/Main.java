package Implementa;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

    public static void main(String[] args) {

        //Criação do objeto bot com as informações de acesso
        TelegramBot bot = TelegramBotAdapter.build("930998203:AAFWhrECZbiVhydGzjTtsr_WchL41_Zg4Ng");

        //objeto responsável por receber as mensagens
        GetUpdatesResponse updatesResponse;
        //objeto responsável por gerenciar o envio de respostas
        SendResponse sendResponse;
        //objeto responsável por gerenciar o envio de ações do chat
        BaseResponse baseResponse;

        //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
        int m = 0;

        //loop infinito pode ser alterado por algum timer de intervalo curto
        while (true) {

            //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

            //lista de mensagens
            List<Update> updates = updatesResponse.updates();

            //análise de cada ação da mensagem
            for (Update update : updates) {

                //atualização do off-set
                m = update.updateId() + 1;

                System.out.println("Recebendo mensagem:" + update.message().text());

                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

                if (update.message().text().contains("ajuda") || update.message().text().contains("help")) {

                    sendResponse = bot.execute(new SendMessage(update.message()
                            .chat().id(), "Em que posso ajuda-lo..."));

                }
                if (update.message().text().contains("Bom") || update.message().text().contains("Olá") || update.message().text().contains("Boa")) {
                    sendResponse = bot.execute(new SendMessage(update.message()
                            .chat().id(), "Seja Bem Vindo, em que posso ajuda-lo"));
                }

                if (update.message().text().contains("preço") || update.message().text().contains("valor")) {
                    sendResponse = bot.execute(new SendMessage(update.message()
                            .chat().id(), "Qual produto deseja saber o preço"));
                }
                if (update.message().text().contains("Lista") || update.message().text().contains("lista")) {
                    sendResponse = bot.execute(new SendMessage(update.message()
                            .chat().id(), "Estou anotando sua lista"));
                }
                if (update.message().text().contains("disponivel") || update.message().text().contains("disponibilidade")) {
                    sendResponse = bot.execute(new SendMessage(update.message()
                            .chat().id(), "Aguarde estamos consultando nosso estoque"));
                }
                
                

            }

        }

    }

}
