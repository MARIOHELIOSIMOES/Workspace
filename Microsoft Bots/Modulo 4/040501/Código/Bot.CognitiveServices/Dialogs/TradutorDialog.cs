using Microsoft.Bot.Builder.Dialogs;
using Microsoft.Bot.Builder.Luis;
using Microsoft.Bot.Builder.Luis.Models;
using Microsoft.Bot.Connector;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Xml.Linq;

namespace Bot.CognitiveServices.Dialogs
{


    [Serializable]

    public class TradutorDialog : LuisDialog<object>
    {
        enum FLUXO { INICIO, TEXTO, IDIOMA, CONFIRMA, FINAL };
        string[] sigla = new string[] { "pt-br", "en-us", "es-es", "zh-cn", "de-de" };
        string[] idioma = new string[] { "Português", "Inglês", "Espanhol", "Chinês", "Alemão" };
        
        FLUXO Fluxo;
        Resposta resposta = new Resposta();

        private readonly string _translateApiKey = ConfigurationManager.AppSettings["TranslateApiKey"];
        private readonly string _translateUri = ConfigurationManager.AppSettings["TranslateUri"];

        public TradutorDialog(params ILuisService[] services) : base(services)
        {

        }

        [LuisIntent("None")]
        public async Task NoneAsync(IDialogContext context, LuisResult result)
        {
            await context.PostAsync("Desculpe, eu não entendi...\n" +
                                    "Lembre-se que sou um bot e meu conhecimento é limitado.");
            context.Done<string>(null);
        }

        [LuisIntent("Sobre")]
        public async Task SobreAsync(IDialogContext context, LuisResult result)
        {
            await context.PostAsync("Sou um Bot tradutor.\n" +
                                    "Posso traduzir textos para diversos idiomas!");
            context.Done<string>(null);
        }

        [LuisIntent("Ajuda")]
        public async Task AjudaAsync(IDialogContext context, LuisResult result)
        {
            await context.PostAsync("Eu traduzo textos, você só precisa me dizer 'quero que me traduza algo' e eu vou te pedir a frase e o idioma!");
            context.Done<string>(null);
        }

        [LuisIntent("Saudacao")]
        public async Task SaudacaoAsync(IDialogContext context, LuisResult result)
        {
            await context.PostAsync("Olá..se precisar de uma tradução é só falar");
            context.Done<string>(null);
        }

        [LuisIntent("Traduzir")]

        private async Task TraduzirAsync(IDialogContext context, LuisResult result)
        {
            await context.PostAsync("Estou pronto para traduzir, me mande um texto!");
            context.Wait(FluxoDaTraducao);
            Fluxo = FLUXO.INICIO;
        }

        private async Task FluxoDaTraducao(IDialogContext context, IAwaitable<IMessageActivity> value)
        {
            switch (Fluxo)
            {
                case FLUXO.INICIO:
                                        
                    var activity = await value as Activity;
                    var message = activity.CreateReply();
                    var text = activity.Text;
                    resposta.TextoOriginal = text;

                    foreach (Attachment a in CriarListadeCards())
                    {
                        message.Attachments.Add(a);
                    }

                    message.AttachmentLayout = AttachmentLayoutTypes.Carousel;
                    message.Text = "Escolha um dos idiomas?";
                    await context.PostAsync(message);
                    Fluxo = FLUXO.IDIOMA;
                    break;

                case FLUXO.IDIOMA:
                    var activityIdioma = await value as Activity;
                    var replay = activityIdioma.CreateReply();
                    var idiomaOpcao = activityIdioma.Text;
                    resposta.Idioma = idiomaOpcao;
                    var heroCard = new HeroCard();
                    heroCard.Text = $"Você quer que eu traduza: **{resposta.TextoOriginal}** \nPara o idioma: **{idioma[Array.IndexOf(sigla.ToArray(),resposta.Idioma)]}**";
                    heroCard.Title = "Confirma?"; //heroCard.Subtitle = "Confirma??";
                    heroCard.Buttons = new List<CardAction> { new CardAction { Title = "Sim", Value = "Sim"}, new CardAction { Title = "Não", Value = "Não" } };
                    replay.Attachments.Add(heroCard.ToAttachment());
                    await context.PostAsync(replay);
                    Fluxo = FLUXO.CONFIRMA;
                    break;

                case FLUXO.CONFIRMA:
                    var confirmaActivity = await value;
                    var confirma = confirmaActivity.Text;
                    if (confirma.Equals("Sim") || confirma.Equals("sim"))
                    {
                        await context.PostAsync("Traduzindo..");
                        resposta.TextoTraduzido = await TraducaoOnline(resposta.TextoOriginal, resposta.Idioma);
                        await context.PostAsync($"Eis aqui a tradução: **{resposta.TextoTraduzido}**");
                        Fluxo = FLUXO.FINAL;
                    }
                    else
                    {
                        await context.PostAsync($"Vamos do Inicio então.. \n Qual o texto deseja que eu traduza?");
                        Fluxo = FLUXO.INICIO;
                    }
                    break;
                case FLUXO.FINAL:
                    break;
            }

            if (Fluxo != FLUXO.FINAL)
            {
                context.Wait(FluxoDaTraducao); 
            }
            else
            {
                context.Done<string>(null);
            }
        }
        
        private List<Attachment> CriarListadeCards()
        {
            List<Attachment> lista = new List<Attachment>();
            lista.Add(CriarHeroCard("https://images-na.ssl-images-amazon.com/images/I/41Corvxl0sL.jpg", "Português Brasileiro","pt-br"));
            lista.Add(CriarHeroCard("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Flag_of_the_United_States.svg/2000px-Flag_of_the_United_States.svg.png", "Inglês Americano","en-us"));
            lista.Add(CriarHeroCard("https://cdn.pixabay.com/photo/2014/11/06/00/51/spain-518688_960_720.png", "Espanhol Espanha","es-es"));
            lista.Add(CriarHeroCard("https://imagepng.org/wp-content/uploads/2017/10/bandeira-china.png", "Mandarim China","zh-cn"));
            lista.Add(CriarHeroCard("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8ixAapcpxx9v9xJ620_K3iZUNi-Pnvn8lcCoriwULe25eAvtckQ", "Alemão Alemanha","de-de"));
            return lista;
        }
        private Attachment CriarHeroCard(string url,string descricao, string valor)
        {
           
        //var activity = await result as Activity;
        //var message = activity.CreateReply();
        var heroCard = new HeroCard();
            heroCard.Title = descricao;
            heroCard.Subtitle = valor;
            heroCard.Images = new List<CardImage> { new CardImage(""+url,descricao)};
            heroCard.Buttons = new List<CardAction> { new CardAction { Title = descricao, Value = valor, Image = url }};
           
            return heroCard.ToAttachment();
        }
        public async Task<string> TraducaoOnline(string texto, string idioma)
        {
            var client = new HttpClient();
            client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", _translateApiKey);

            var uri = _translateUri + "?to=" + idioma +
                      "&text=" + System.Net.WebUtility.UrlEncode(texto);

            var response = await client.GetAsync(uri);
            var result = await response.Content.ReadAsStringAsync();
            var traducao = XElement.Parse(result).Value;
            
            return traducao.ToString();
        }


    }
    [Serializable]
    public class Resposta
    {
        public Resposta()
        {

        }
        public string TextoOriginal { get; set; }
        public string Confirmar { get; set; }
        public string TextoTraduzido { get; set; }
        public string Idioma { get; internal set; }
    }
}