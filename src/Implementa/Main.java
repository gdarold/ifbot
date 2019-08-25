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

        TelegramBot bot = TelegramBotAdapter.build("930998203:AAFWhrECZbiVhydGzjTtsr_WchL41_Zg4Ng");

        GetUpdatesResponse updatesResponse;

        SendResponse sendResponse;

        BaseResponse baseResponse;

        int m = 0;

        while (true) {

            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

            List<Update> updates = updatesResponse.updates();

            for (Update update : updates) {
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
