package simoes.mario.controlesalarial.GUI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import java.util.ArrayList;
import java.util.List;

import simoes.mario.controlesalarial.Firebase.FireDespesas;
import simoes.mario.controlesalarial.Firebase.FireSalario;
import simoes.mario.controlesalarial.R;
import simoes.mario.controlesalarial.constantes.Constantes;
import simoes.mario.controlesalarial.modelos.Despesas;
import simoes.mario.controlesalarial.modelos.Salario;
import simoes.mario.controlesalarial.negocio.SalarioBusiness;

public class Graficos extends AppCompatActivity {


    FireSalario fireSalario;
    FireDespesas fireDespesas;

    SalarioBusiness sb;

    List<Salario> salarioList;
    List<Despesas> despesasList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_graficos);
        setContentView(R.layout.activity_graficos);

        fireSalario = new FireSalario();
        fireDespesas = new FireDespesas();

        fireSalario.getReference().addValueEventListener(listenerFireSalario);
        fireDespesas.getReference().addValueEventListener(listenerFireDespesa);

        salarioList = new ArrayList<>();
        despesasList = new ArrayList<>();
    }

    ValueEventListener listenerFireDespesa = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                fireDespesas.ClearListDespesas();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    fireDespesas.setDespesa(child.getValue(Despesas.class));
                    fireDespesas.AddDespesaLista(fireDespesas.getDespesa());
                }
                Log.d("Graficos", "Tamanho da lista de despesas: " + fireDespesas.getListDespesas().size());
                despesasList.clear();
                despesasList = fireDespesas.getListDespesas();
                carregarGraficos();
                //   progressDialog.dismiss();
            } catch (Exception e) {
                Log.d("Graficos!", "Erro no DespesasListener , message: " + e.getMessage());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerFireSalario = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                fireSalario.ClearListSalario();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    fireSalario.setSalario(child.getValue(Salario.class));
                    fireSalario.AddSalarioLista(fireSalario.getSalario());
                }
                Log.d("Graficos", "Tamanho da lista de salários: "+fireSalario.getListSalario().size());
                salarioList.clear();
                salarioList = fireSalario.getListSalario();
                carregarGraficos();
                //   progressDialog.dismiss();
            }catch(Exception e){
                Log.d("Graficos!","Erro no Salariolistener , message: " + e.getMessage());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    private void carregarGraficos() {

        try{
            GraphView.GraphViewData[] gvdSalarios = new GraphView.GraphViewData[salarioList.size()];
            GraphView.GraphViewData[] gvdDespesasMes = new GraphView.GraphViewData[salarioList.size()];
            GraphView.GraphViewData[] gvdDespesasTotais = new GraphView.GraphViewData[despesasList.size()];
            GraphView.GraphViewData[] gvdSaldoMes = new GraphView.GraphViewData[salarioList.size()];

            String [] labelsMes = new String[salarioList.size()];
            String [] labelsDespesas = new String[despesasList.size()];
            String [] labelsDescricao = new String[despesasList.size()];

            double despesaMes=0, despesasGerais =0, salario=0, saldoMes=0;

            for (int i = 0; i <salarioList.size(); i++) {
                salario = salarioList.get(i).getValor();
                despesaMes = fireDespesas.getTotalDespesasPorID(salarioList.get(i).getId());
                saldoMes = salario - despesaMes;
                labelsMes[i]=""+ Constantes.MESES_MENOR[salarioList.get(i).getMes()]+"/" + salarioList.get(i).getAno();

                gvdSalarios[i] = new GraphView.GraphViewData(i, salario);
                gvdDespesasMes[i] = new GraphView.GraphViewData(i, despesaMes);
                gvdSaldoMes[i] = new GraphView.GraphViewData(i, saldoMes);
            }
            for(int j =0; j< despesasList.size(); j++){
                despesasGerais = despesasList.get(j).getValor();
                labelsDespesas[j]=""+ Constantes.MESES_MENOR[fireSalario.getSalarioPorID(despesasList.get(j).getId_mes()).getMes()]
                        +"/" + fireSalario.getSalarioPorID(despesasList.get(j).getId_mes()).getAno();
                labelsDescricao[j]=despesasList.get(j).getNome();

                gvdDespesasTotais[j] = new GraphView.GraphViewData(j,despesasGerais);
            }


            GraphViewSeries seriesSalarios = new GraphViewSeries("Salários",
                        new GraphViewSeries.GraphViewSeriesStyle(Color.GREEN, 3), gvdSalarios);
            GraphViewSeries seriesDespesas = new GraphViewSeries("Despesas do Mês",
                    new GraphViewSeries.GraphViewSeriesStyle(Color.RED, 3), gvdDespesasMes);
            GraphViewSeries seriesSaldo = new GraphViewSeries("Saldo",
                    new GraphViewSeries.GraphViewSeriesStyle(Color.CYAN, 3), gvdSaldoMes);
            GraphViewSeries seriesDespesasTotais = new GraphViewSeries("Despesas do Totais",
                    new GraphViewSeries.GraphViewSeriesStyle(Color.YELLOW, 3), gvdDespesasMes);


            // LineGraphView grafGeral = new LineGraphView(this,"Gráfico de Salários, Despesas e Saldo");
            BarGraphView grafGeral = new BarGraphView(this, "Gráfico de Salários, Despesas e Saldo");
      /*      BarGraphView grafSalarios = new BarGraphView(this, "Gráficos de Salários");
            BarGraphView grafSaldos = new BarGraphView(this, "Gráficos de Saldos");
            BarGraphView grafDespesas = new BarGraphView(this, "Gráficos de Despesas");
    */
        //    LineGraphView grafGeral = new LineGraphView(this, "Gráfico de Salários, Despesas e Saldo");
            LineGraphView grafSalarios = new LineGraphView(this, "Gráficos de Salários");
            LineGraphView grafSaldos = new LineGraphView(this, "Gráficos de Saldos");
            LineGraphView grafDespesas = new LineGraphView(this, "Gráficos de Despesas");

            grafGeral.addSeries(seriesSalarios);
            grafGeral.addSeries(seriesDespesas);
            grafGeral.addSeries(seriesSaldo);

            grafSalarios.addSeries(seriesSalarios);
            grafDespesas.addSeries(seriesDespesasTotais);
            grafSaldos.addSeries(seriesSaldo);

            grafGeral.setShowLegend(true);
            grafGeral.setLegendAlign(GraphView.LegendAlign.BOTTOM);

            grafGeral.setDrawValuesOnTop(true);
            grafGeral.setValuesOnTopColor(Color.BLACK);

            grafSalarios.setShowLegend(true);
            grafSalarios.setLegendAlign(GraphView.LegendAlign.BOTTOM);
            //grafSalarios.setDrawValuesOnTop(true);
            //grafSalarios.setValuesOnTopColor(Color.BLACK);

            grafSaldos.setShowLegend(true);
            grafSaldos.setLegendAlign(GraphView.LegendAlign.BOTTOM);
            //grafSaldos.setDrawValuesOnTop(true);
            //grafSaldos.setValuesOnTopColor(Color.BLACK);

            grafDespesas.setShowLegend(true);
            grafDespesas.setLegendAlign(GraphView.LegendAlign.BOTTOM);
            //grafDespesas.setDrawValuesOnTop(true);
            //grafDespesas.setValuesOnTopColor(Color.BLACK);

            //grafSalarios.setDrawValuesOnTop(true);
            //grafSaldos.setDrawValuesOnTop(true);
            //grafDespesas.setDrawValuesOnTop(true);

            grafGeral.getGraphViewStyle().setGridColor(Color.GRAY);
            grafGeral.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
            grafGeral.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
            grafGeral.getGraphViewStyle().setTextSize(15);

            grafDespesas.getGraphViewStyle().setGridColor(Color.GRAY);
            grafDespesas.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
            grafDespesas.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
            grafDespesas.getGraphViewStyle().setTextSize(15);

            grafSalarios.getGraphViewStyle().setGridColor(Color.GRAY);
            grafSalarios.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
            grafSalarios.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
            grafSalarios.getGraphViewStyle().setTextSize(15);

            grafSaldos.getGraphViewStyle().setGridColor(Color.GRAY);
            grafSaldos.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
            grafSaldos.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
            grafSaldos.getGraphViewStyle().setTextSize(15);

            //grafGeral.setVerticalLabels(new String[]{"y1", "y2", "y3", "y4"});
            grafGeral.setHorizontalLabels(labelsMes);
            grafSalarios.setHorizontalLabels(labelsMes);
            grafSaldos.setHorizontalLabels(labelsMes);
            grafDespesas.setHorizontalLabels(labelsDespesas);

             //   grafGeral.setViewPort(1, 3);
            grafGeral.setScrollable(true);
            grafGeral.setScalable(true);
            grafSalarios.setScrollable(true);
            grafSalarios.setScalable(true);
            grafSaldos.setScrollable(true);
            grafSaldos.setScalable(true);
            grafDespesas.setScrollable(true);
            grafDespesas.setScalable(true);

            // grafGeral.setDrawBackground(true);
            //  grafGeral.setBackgroundColor(Color.BLUE);
            LinearLayout layoutGeral = (LinearLayout) findViewById(R.id.graficoGeral);
            layoutGeral.addView(grafGeral);
            LinearLayout layoutSalario = (LinearLayout) findViewById(R.id.graficoSalarios);
            layoutSalario.addView(grafSalarios);
            LinearLayout layoutSaldo = (LinearLayout) findViewById(R.id.graficoSaldos);
            layoutSaldo.addView(grafSaldos);
            LinearLayout layoutDespesas = (LinearLayout) findViewById(R.id.graficoDespesas);
            layoutDespesas.addView(grafDespesas);
        }catch (Exception e){
            Log.e("Erro", e.getMessage());
            showToast(e.getMessage().toString());
        }
    }
    private void showToast(String mensagem){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }
}

        /*

        int i, tam = 200;
        double v = 0;

        // SENO
      GraphView.GraphViewData[] data = new GraphView.GraphViewData[tam];
        for(i = 0; i < tam; i++){
            v += 0.3;
            data[i] = new GraphView.GraphViewData(i, Math.sin(v));
        }
        GraphViewSeries seriesSeno = new GraphViewSeries("Seno", new GraphViewSeries.GraphViewSeriesStyle(Color.BLUE, 3), data);

        // COSSENO
        data = new GraphView.GraphViewData[tam];
        for(i = 0; i < tam; i++){
            v += 0.3;
            data[i] = new GraphView.GraphViewData(i, Math.cos(v));
        }
        GraphViewSeries seriesCosseno = new GraphViewSeries("Cosseno", new GraphViewSeries.GraphViewSeriesStyle(Color.GREEN, 3), data);


        LineGraphView graph = new LineGraphView(this, "Exemplo GraphView");
        graph.addSeries(seriesSeno);
        graph.addSeries(seriesCosseno);

        graph.setShowLegend(true);
        graph.setLegendAlign(GraphView.LegendAlign.BOTTOM);

        graph.getGraphViewStyle().setGridColor(Color.GRAY);
        graph.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
        graph.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
        graph.getGraphViewStyle().setTextSize(15);

		/*graph.setVerticalLabels(new String[]{"y1", "y2", "y3", "y4"});
		graph.setHorizontalLabels(new String[]{"x1", "x2", "x3", "x4"});*/

		/*graph.setCustomLabelFormatter(new CustomLabelFormatter(){
			@Override
			public String formatLabel(double value, boolean isValueX) {
				// TODO Auto-generated method stub
				if(isValueX){
					if(value > 2){
						return("x1");
					}
					return("x2");
				}
				else{
					if(value > 2){
						return("y1");
					}
					return("y2");
				}
		}});

        graph.setViewPort(10, 30);
        graph.setScrollable(true);
        graph.setScalable(true);

		/*graph.setDrawBackground(true);
		graph.setBackgroundColor(Color.BLUE);

        LinearLayout ll = (LinearLayout) findViewById(R.id.graficoSaldos);
        ll.addView(graph);

        */



