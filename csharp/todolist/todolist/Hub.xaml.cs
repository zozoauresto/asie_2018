using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Windows.Storage;
using SQLitePCL;
using System.Collections.ObjectModel;
using Microsoft.Toolkit.Uwp.Helpers;

// Pour plus d'informations sur le modèle d'élément Page vierge, consultez la page https://go.microsoft.com/fwlink/?LinkId=234238

namespace todolist
{
    /// <summary>
    /// Une page vide peut être utilisée seule ou constituer une page de destination au sein d'un frame.
    /// </summary>
    public sealed partial class Hub : Page
    {
        //Boolean bouttonview = false;
        //SQLitePCL.SQLiteConnection dbConnection = new SQLiteConnection("Folders.db");

        private ObservableCollection<Task> tasks = new ObservableCollection<Task>();
        public static int SelectedFdp = -1;

        public Hub()
        {
            this.InitializeComponent();
            fdpdesouche();
            Application.Current.Suspending += new SuspendingEventHandler(app_suck);
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            tasks.Add(new Task(InputBox.Text, DescBox.Text, DateBox.Date.ToString("d")));
            paslistBox.ItemsSource = tasks;
        }

        private void removeButton_Click(object sender, RoutedEventArgs e)
        {
            foreach (var task in tasks.ToList())
            {
                if (task.Checked)
                {
                    tasks.Remove(task);
                }
            }
        }

        private void CheckBox_Checked(object sender, RoutedEventArgs e)
        {
            var checkbox = sender as CheckBox;
            StackPanel text = checkbox.Content as StackPanel;

            TextBlock t = text.Children.First() as TextBlock;


            foreach (var task in tasks)
            {
                if (task.Id.ToString() == t.Text.ToString())
                {
                    task.Checked = true;
                }
            }
        }

        private void CheckBox_RightTapped(object sender, RightTappedRoutedEventArgs e)
        {
            var checkbox = sender as CheckBox;
            StackPanel text = checkbox.Content as StackPanel;

            TextBlock t = text.Children.First() as TextBlock;


            foreach (var task in tasks)
            {
                if (task.Id.ToString() == t.Text.ToString())
                {
                    InputBox.Text = task.Title;
                    DescBox.Text = task.Desc;
                    task.Checked = true;
                    SelectedFdp = task.Id;
                }
            }
        }

        public void ntmFdp()
        {
            var ntm = new LocalObjectStorageHelper();
            ntm.Save("fdp", tasks);
        }

        public void fdpdesouche()
        {
            var ntm = new LocalObjectStorageHelper();

            if (ntm.KeyExists("fdp"))
            {
                tasks = ntm.Read<ObservableCollection<Task>>("fdp");
                paslistBox.ItemsSource = tasks;
            }
        }

        async void app_suck(object sender, Windows.ApplicationModel.SuspendingEventArgs e)
        {
            ntmFdp();
        }

        public void Button_Click_1(object sender, RoutedEventArgs e)
        {
            for (int i = 0; i < tasks.Count; i++)
            {
                if (tasks[i].Id == SelectedFdp)
                {
                    tasks[i].Title = InputBox.Text;
                    tasks[i].Desc = DescBox.Text;
                    tasks[i].Date = DateBox.Date.ToString("d");
                }
            }
        }

        public void sort_id(object sender, RoutedEventArgs e)
        {
            tasks = new ObservableCollection<Task>(tasks.OrderBy(o => o.Id).ToList());
            paslistBox.ItemsSource = tasks;
        }

        public void sort_done(object sender, RoutedEventArgs e)
        {
            tasks = new ObservableCollection<Task>(tasks.OrderBy(o => o.Title).ToList());
            paslistBox.ItemsSource = tasks;
        }
    }
}
