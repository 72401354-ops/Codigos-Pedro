using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculadoraPedro
{
    internal class Program
    {
        static void Main(string[] args)
        {

            while (true)
            {


                Console.Clear();
                Console.WriteLine("=== CALCULADORA ===");
                Console.WriteLine("1 - Soma");
                Console.WriteLine("2 - Subtração");
                Console.WriteLine("3 - Multiplicação");
                Console.WriteLine("4 - Divisão");
                Console.WriteLine("5 - Sair");
                Console.WriteLine("# - Historico de calculos");
                Console.WriteLine("===================");
                Console.Write("Escolha uma opção: ");

                string opcao = Console.ReadLine();

                if (opcao == "5")
                    break;

                switch (opcao)
                {
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                        Calcular(opcao);
                        break;

                    case "#":
                        MostrarHistorico();
                        break;
                    default:
                        Console.Writeline("Opçao invalida");
                        break;

                }

                Console.WriteLine("\nPressione qualquer tecla para continuar...");
                Console.ReadKey();

            }

        }

        static void Calcular(String operacao)
        {
            Console.WriteLine("Digite o primiro numero: ");
            double num1 = Convert.ToDouble(Console.ReadLine());

            Console.WriteLine("Digite o segundo numero: ")
            double num2 = Convert.ToDouble(Console.ReadLine());

            double resultado = 0;
            string operador = "";
            string operacaoDesc = "";


            switch (operacao)
            {
                case "1":
                    resultado = num1 + num2;
                    operador = "+";
                    operacaoDesc = "soma";
                    break;

                case "2":
                    resultado = num1 - num2;
                    operador = "-";
                    operacaoDesc = "subtracao";
                    break;

                case "3":
                    resultado = num1 * num2;
                    operador = "x";
                    operacaoDesc = "multiplicacao";
                    break;

                case "4":
                    if (num2 == 0)
                    {
                        Console.WriteLine("Erro: Divisao por Zero")
                            return;
                    }
                    resultado = num1 / num2;
                    operador = "/";
                    operacaoDesc = "divisao";
                    break;

            }

            string calculo = $"{num1} {operador} {num2} = {resultado}"
            MostrarHistorico.Add($"{operacaoDesc}: {calculo}");

            Console.WriteLine($"Resultado: {calculo}");

        }

        static void MostrarHistorico()
        {
            Console.WriteLine("\n=== HISTORICO DE CALCULOS ===");
            if(historico.Count == 0)
            {
                Console.WriteLine("Nenhum calculo realizado")
            }
            else
            {
                for (int i = 0; i < historico.Count; i++)
                {
                    Console.WriteLine($"{i + 1}. {MostrarHistorico[i]}");
                }
            }
        }

           
            



        
    }
}
